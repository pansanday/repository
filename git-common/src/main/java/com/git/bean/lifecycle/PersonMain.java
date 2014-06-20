package com.git.bean.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonMain {

    public static void main(String[] args) {
        // 如果要调用销毁方法必须用子类来声明,而不是ApplicationContext,因为ApplicationContext没有close()
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person p1 = (Person) ac.getBean("person");
        Person p2 = (Person) ac.getBean("person");
        ac.close();
    }
}
