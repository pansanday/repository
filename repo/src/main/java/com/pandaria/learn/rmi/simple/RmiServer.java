package com.pandaria.learn.rmi.simple;

import com.pandaria.learn.rmi.HelloServiceImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 通过 JVM 提供的 JNDI（Java Naming and Directory Interface，Java 命名与目录接口）这个 API 发布 RMI 服务
 */
public class RmiServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        int port = 1099;
        // RMI协议URL: rmi://<host>:<port>/<service>
        String url = "rmi://localhost:1099/com.pandaria.learn.rmi.HelloServiceImpl";
        // 在JNDI中创建一个注册表
        LocateRegistry.createRegistry(port);
        // 绑定RMI地址与RMI服务实现类
        Naming.rebind(url, new HelloServiceImpl());
        System.out.println("RmiServer started...");
    }
}
