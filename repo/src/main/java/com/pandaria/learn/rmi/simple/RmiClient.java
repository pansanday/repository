package com.pandaria.learn.rmi.simple;

import com.pandaria.learn.rmi.HelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 调用RMI服务
 */
public class RmiClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // RMI请求路径
        String url = "rmi://localhost:1099/com.pandaria.learn.rmi.HelloServiceImpl";
        HelloService helloService = (HelloService) Naming.lookup(url);
        String result = helloService.sayHello("Panda");
        System.out.println(result);
    }
}
