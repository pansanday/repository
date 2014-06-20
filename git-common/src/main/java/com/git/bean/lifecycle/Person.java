package com.git.bean.lifecycle;

public class Person {

    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Person() {
        System.out.println("实例化一个对象");
    }

    public void init() {
        System.out.println("调用初始化方法....");
    }

    public void destory() {
        System.out.println("调用销毁化方法....");
    }
}
