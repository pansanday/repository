package com.pandaria.learn.rmi.zookeeper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

/**
 * 服务提供者
 */
public class ServiceProvider {

    private final Logger logger = LogManager.getLogger();

    private CountDownLatch latch = new CountDownLatch(1);

    /**
     * 发布RMI服务
     *
     * @param remote 远程对象
     * @param host   域名
     * @param port   端口号
     */
    private String publishService(Remote remote, String host, int port) {
        String url = null;

        try {
            url = String.format("rmi://%s:%d/%s", host, port, remote.getClass().getName());
            LocateRegistry.createRegistry(port);
            Naming.rebind(url, remote);
            logger.debug("publish rmi service (url: {})", url);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 连接ZooKeeper服务器
     *
     * @return ZooKeeper对象
     */
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;

        try {
            zk = new ZooKeeper(Constant.ZK_CONNECTION_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        // 唤醒当前正在执行的线程
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return zk;
    }

    /**
     * 使用 ZooKeeper 的临时性 ZNode 来存放服务提供者的 RMI 地址，一旦与服务提供者的 Session 中断，会自动清除相应的 ZNode
     *
     * @param zk  ZooKeeper对象
     * @param url rmi地址
     */
    private void createNode(ZooKeeper zk, String url) {
        try {
            byte[] data = url.getBytes();
            // 创建一个临时性且有序的 ZNode
            String path = zk.create(Constant.ZK_PROVIDER_PATH, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.debug("create zookeeper node ({} => {})", path, url);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布 RMI 服务并注册 RMI 地址到 ZooKeeper 中
     *
     * @param remote 远程对象
     * @param host   服务器
     * @param port   端口号
     */
    public void publish(Remote remote, String host, int port) {
        // 发布 RMI 服务并返回 RMI 地址
        String url = publishService(remote, host, port);
        if (url != null) {
            // 连接 ZooKeeper 服务器并获取 ZooKeeper 对象
            ZooKeeper zk = connectServer();
            if (zk != null) {
                // 创建 ZNode 并将 RMI 地址放入 ZNode 上
                createNode(zk, url);
            }
        }
    }
}
