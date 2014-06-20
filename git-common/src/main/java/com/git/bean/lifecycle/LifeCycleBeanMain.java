package com.git.bean.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleBeanMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        LifeCycleBean lifeCycleBean = (LifeCycleBean) context.getBean("lifeCycleBean");
        context.close();
    }
}
