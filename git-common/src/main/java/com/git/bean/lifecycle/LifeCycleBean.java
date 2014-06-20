package com.git.bean.lifecycle;

import org.springframework.beans.factory.InitializingBean;

public class LifeCycleBean implements InitializingBean {

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("implements initializing stat...");
        if (null == name) {
            System.out.println("configuration failed!");
        }
        System.out.println("Implements initializing end!");
    }

    public void inits() {
        System.out.println("init() initializing start...");
        if (null == name) {
            System.out.println("configuration failed!");
        }
        System.out.println("init() initializing end!");
    }

    public void destroy() {
        System.out.println("destory()....");
    }

}
