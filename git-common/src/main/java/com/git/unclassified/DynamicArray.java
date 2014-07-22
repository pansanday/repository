package com.git.unclassified;

import java.util.Arrays;

/**
 * 动态数组并不是真正的动态，只是通过数组的拷贝来实现动态的增加
 * 
 * @author pansanday
 */
public class DynamicArray {

    public static void main(String[] args) {
        // 定义一个int类型的数组，其长度为0
        int numArray[] = {};
        for (int i = 0; i < 10; i++) {
            // 数组每次动态增加一个长度，并赋值给数组
            numArray = Arrays.copyOf(numArray, numArray.length + 1);
            numArray[numArray.length - 1] = i;
            // 打印出数组的各个元素
            System.out.println(Arrays.toString(numArray));
        }
    }
    
	public String changeStringOrder(String string) {

		char[] temp = string.toCharArray(); // 将string字符串转换成char[]数组
		String str = "";
		for (int i = 1; i <= temp.length; i++) {
			str += temp[temp.length - i]; // 通过循环遍历,将尾元素以此加入到str上
		}
		return str;
	}
}

/**
 * int[] java.util.Arrays.copyOf(int[] original, int newLength)
 * 
 * @param original
 *            the array to be copied
 * @param newLength
 *            the length of the copy to be returned
 */