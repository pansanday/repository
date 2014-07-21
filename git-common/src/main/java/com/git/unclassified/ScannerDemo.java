package com.git.unclassified;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ScannerDemo {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in); // 从键盘输入数字
		int j = 0;
		int positiveCount = 0;
		int zeroCount = 0;
		int nagetiveCount = 0;

		Integer[] a = new Integer[5];
		for (int i = 0; i < 5; i++) {
			System.out.print("请输入第" + i + "个数:");
			j = scan.nextInt(); // 输入下一个数字
			a[i] = j;
			if (a[i] > 0) {
				positiveCount++;
			} else if (a[i] == 0) {
				zeroCount++;
			} else {
				nagetiveCount++;
			}
		}
		Arrays.sort(a, Collections.reverseOrder()); // 倒序排列的方法
		System.out.println("所输入的数是:" + printArray(a));
		System.out.println("正数有:" + positiveCount + "个\t" + "负数有:"
				+ nagetiveCount + "个\t" + "零有:" + zeroCount + "个");
	}

	public static String printArray(Integer[] o) {
		String temp = "";
		if (null != o && o.length != 0) {
			for (int i = 0; i < o.length; i++) {
				temp += o[i] + " ";
			}
		}
		return temp;
	}
}
