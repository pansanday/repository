package com.git.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {

	private int countDown = 5;
	private volatile double d;	//变量d是volatile的,以努力确保不进行任何编译器优化
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	/**
	 * toString方法被覆盖,以便使用Thread.toString()方法来打印线程名称,线程优先级以及线程所属的线程组
	 */
	public String toString() {
		return Thread.currentThread() + ": " + countDown;
	}

	public void run() {
		Thread.currentThread().setPriority(priority);
		while (true) {
			for (int i = 0; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}

}
