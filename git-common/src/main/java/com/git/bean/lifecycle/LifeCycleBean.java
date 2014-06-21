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

	/**
	 * bean在配置文件中被实例化之后,会调用这个方法进行初始化,因为配置了init-method="inits";
	 * 这个inits名称可以随意指定,只要保证和配置文件中名称相同就行
	 */
    public void inits() {
        System.out.println("init() initializing start...");
        if (null == name) {
            System.out.println("configuration failed!");
        }
        System.out.println("init() initializing end!");
    }

    /**
     * applicaton context在被关闭后,会在bean工厂中销毁所有的bean,此时调用该方法
     * 同理也是因为配置了destroy-method="destroy",destroy这个名称可变
     */
    public void destroy() {
        System.out.println("destory()....");
    }

}
