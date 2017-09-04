package com.pandaria.learn.core;

public class MultiParam {

    /**
     * 多参数示例
     * @param withNullValue 多个参数,实际就是个数组
     */
    private void update(boolean... withNullValue) {

        if (withNullValue !=null && withNullValue.length > 0 && withNullValue[0]) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }

    public static void main(String[] args) {
        new MultiParam().update(true);
    }
}
