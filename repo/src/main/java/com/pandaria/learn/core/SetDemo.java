package com.pandaria.learn.core;

import java.util.HashSet;
import java.util.Set;

/**
 * Set集合的并集、合集、差集示例
 */
public class SetDemo {

    public static void main(String[] args) {
        Set<String> result = new HashSet<>();
        Set<String> setOne = new HashSet<>();
        setOne.add("张三");
        setOne.add("李四");
        setOne.add("王五");
        setOne.add("田六");
        setOne.add("江七");

        Set<String> setTwo = new HashSet<>();
        setTwo.add("李四");
        setTwo.add("田六");
        setTwo.add("胡八");

        result.clear();
        result.addAll(setOne);
        result.addAll(setTwo);
        System.out.println("并集:" + result);

        result.clear();
        result.addAll(setOne);
        result.retainAll(setTwo);
        System.out.println("交集:" + result);

        result.clear();
        result.addAll(setOne);
        result.removeAll(setTwo);
        System.out.println("差集:" + result);
    }
}