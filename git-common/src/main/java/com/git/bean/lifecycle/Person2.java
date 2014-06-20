package com.git.bean.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Person2 {

    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Person2() {
        System.out.println("实例化一个对象");
    }

    // 初始化方法的注解方式 等同与init-method=init
    @PostConstruct
    public void init() {
        System.out.println("调用初始化方法....");
    }

    // 销毁方法的注解方式 等同于destory-method=destory222
    @PreDestroy
    public void destory() {
        System.out.println("调用销毁化方法....");
    }
}
