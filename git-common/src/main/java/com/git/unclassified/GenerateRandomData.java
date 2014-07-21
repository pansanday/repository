package com.git.unclassified;

import java.util.Arrays;
import java.util.Random;

public class GenerateRandomData {

	public static void main(String[] args) {
		Random random = new Random();
		String string = "";
		int j = 0;
		int array[] = new int[10];
		for (int i = 0; i < 10; i++) {
			// random.nextInt(100)表示生成[0,100)之间的数字,包含0,不包含100
			j = random.nextInt(100) + 1; // [1,101)之间的数字,即[1-100]
			array[i] = j;
			string = string + j + "\t"; // \t表示一个tab的距离
		}
		System.out.println("随机生成的序列是:");
		System.out.println(string);
		Arrays.sort(array); // 对数组进行升序(默认)排列
		System.out.println("此数列的最大值是:" + array[array.length - 1]);
		System.out.println("此数列的最小值是:" + array[0]);
	}
}
