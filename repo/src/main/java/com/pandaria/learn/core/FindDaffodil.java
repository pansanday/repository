package com.pandaria.learn.core;

/**
 * 找出100——999数中的所有水仙花数
 * 水仙花数是指：一个三位数，其各位数字立方和等于该数本身。例如：370=33+73+00.这就说明370是一个水仙花数。
 */
public class FindDaffodil {

    public static void main(String[] args) {

        for (int i = 100; i < 1000; i++) {

            int x = i / 100; // 百位数
            int y = (i % 100) / 10; // 十位数
            int z = (i % 100) % 10; // 个位数

            if (i == Math.pow(x, 3) + Math.pow(y, 3) + Math.pow(z, 3)) {
                System.out.println("I find one! It's " + i);
            }
        }

    }
}
