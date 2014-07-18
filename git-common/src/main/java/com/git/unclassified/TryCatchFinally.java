package com.git.unclassified;

public class TryCatchFinally {
	public static void main(String[] args) {
		System.out.println(sayHello("Panda"));
		// sayHello("Panda"); // 虽然有return值,但是没有打印出来,要注意!
	}

	public static String sayHello(String string) {

		try {
			int i = 10;
			int j = 0;
			System.out.println(i / j); // 1 - 运行到这行,有错误,跳到catch中
			return "try return";
		} catch (ArithmeticException e) {
			System.out.println("Exception happened..."); // 2- 打印输出语句
			e.printStackTrace(); // 3 - 打印错误消息
			return "catch return"; // 5 - 最后再return;如果最外层没有return,这里的return一定要有!
		} finally {
			System.out.println("In finally statements"); // 4 - 进入finally 语句
			// return "";
		}

		// return "Hello " + string;
	}
}
