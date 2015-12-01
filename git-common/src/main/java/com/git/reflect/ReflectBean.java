package com.git.reflect;

public class ReflectBean {

    private int fruit;

    public static final double PI = 3.1415926;

    public static final ReflectUser reflectUser = new ReflectUser();

    public int getFruit() {
        return fruit;
    }

    public void setFruit(int fruit) {
        this.fruit = fruit;
    }

    public String sayHello(String name) {
        return "Hello " + name;
    }

}
