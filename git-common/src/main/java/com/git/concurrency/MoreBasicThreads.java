package com.git.concurrency;

public class MoreBasicThreads {

	/**
	 * 不同任务的执行在线程被换进换出时混在了一起,这种交换是由线程调度器自动控制的
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			/**
			 * 一个线程会创建一个单独的执行线程,在对start()的调用完成之后,它仍旧会继续存在
			 * 因为start后,这个线程并不一定执行完成了,可以需要等待时间片
			 */
			new Thread(new LiftOff()).start();
		}
		System.out.println("Waiting for LiftOff");
	}
}
