package com.git.concurrency;

public class BasicThread {

	/**
	 * 实际上有两个线程在并行执行,一个是main方法->输出"Waiting for LiftOff"
	 * 另一个是LiftOff.run()线程->执行倒计时
	 */
	public static void main(String[] args) {
		/**
		 * 显式的将一个任务附着到线程上,这个任务必须是Runnable对象
		 * java.lang.Thread.Thread(Runnable target)
		 */
		Thread thread = new Thread(new LiftOff());
		/**
		 * 调用Thread对象的start()方法为该线程执行必需的初始化操作,然后调用Runnable的run()方法,
		 * 以便在这个新线程中启动该任务
		 */
		thread.start();
		System.out.println("Waiting for LiftOff");
	}
}
