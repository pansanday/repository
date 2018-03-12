package com.pandaria.learn.rmi.zookeeper;

import com.pandaria.learn.rmi.HelloService;

public class Client {

    public static void main(String[] args) throws Exception {
        ServiceConsumer consumer = new ServiceConsumer();

        while (true) {
            HelloService helloService = consumer.lookup();
            String result = helloService.sayHello("Jack");
            System.out.println(result);
            Thread.sleep(5000);
        }
    }
}