/**
 * Copyright 2014-2015 the original author or authors.
 */
package com.git.reflect;

/**
 * @author pansanday
 * @since 2015年10月17日
 */
public class Person implements China {

    private String sex;

    private String name;

    public Person() {

    }

    public Person(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void sayChina() {
        System.out.println("hello ,china");
    }

    public void sayHello(String name, int age) {
        System.out.println(name + "  " + age);
    }

}
