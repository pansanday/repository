package com.git.bean.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Person2Main {

    public static void main(String[] args) {
        // 如果要调用销毁方法必须用子类来声明,而不是ApplicationContext,因为ApplicationContext没有close()
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person2 p1 = (Person2) ac.getBean("person2");
        Person2 p2 = (Person2) ac.getBean("person2");
        ac.close();
    }
}
