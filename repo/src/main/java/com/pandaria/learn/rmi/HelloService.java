package com.pandaria.learn.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程服务类, 必须继承Remote接口(让 JVM 知道该接口是需要用于远程调用的)
 */
public interface HelloService extends Remote {

    /**
     * 远程服务方法,必须声明抛出一个 java.rmi.RemoteException 异常
     *
     * @param name 名称
     * @return 结果
     * @throws RemoteException
     */
    String sayHello(String name) throws RemoteException;
}