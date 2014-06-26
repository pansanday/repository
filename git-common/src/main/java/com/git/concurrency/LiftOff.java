package com.git.concurrency;

/**
 * 线程驱动任务,要想定义任务,只需实现Runnable接口并编写run()方法,使得该任务可以执行你的命令
 * 实现了Runnable接口后,必须实现其中的run()方法
 * @author Pansanday
 */
public class LiftOff implements Runnable {

	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++; // 用来区分任务的多个实例,它是final的,它一旦被初始化后就不希望被修改

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + ")";
	}

	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			/**
			 * 静态方法Thread.yield()表示将CPU从一个线程转移给另一个线程;它在声明:我已经执行完声明周期中最重要的部分了,
			 * 此刻正是切换给其他任务执行一段时间的大好时机
			 */
			Thread.yield();
		}
	}
}
