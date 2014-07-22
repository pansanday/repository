package com.git.unclassified;

public class SingletonDemo {
	// 方法一:
	// private static Singleton singleton;
	//
	// private Singleton(){}
	//
	// public synchronized static Singleton getInstance(){
	// if(singleton == null)
	// return new Singleton();
	// return singleton;
	// }

	// 方法二:
	private SingletonDemo() {
		System.out.println("进入private Singleton()构造函数,第一次调用才会调用");
	}

	private static SingletonDemo instance = new SingletonDemo();

	public static synchronized SingletonDemo getInstance() {
		System.out.println("进入getInstance()方法");
		return instance;
	}

	public static void main(String[] args) {
		SingletonDemo singleton = SingletonDemo.getInstance();
		System.out.println(singleton.toString());
		SingletonDemo singleton2 = SingletonDemo.getInstance();
		if (singleton == singleton2) {
			System.out.println(singleton.toString());
			System.out.println(singleton2.toString());
			System.out.println("两个对象地址相同");
		}
	}
}
