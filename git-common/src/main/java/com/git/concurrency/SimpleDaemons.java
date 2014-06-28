package com.git.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 后台(deamon)线程,指在程序运行时候在后台提供一种通用服务的线程.
 * 当所有非后台线程结束后,程序终止,同时会杀死进程中的所有后台线程
 * 反过来,只要有任何非后台线程还在运行,程序就不会终止
 */
public class SimpleDaemons implements Runnable {
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			System.out.println("sleep() interrupted");
		}
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true); // 必须在线程启动启动之前调用setDeamon()方法,才能将它设置为后台进程
			daemon.start();
		}
		System.out.println("All daemons started");
		// main()线程被设定为短暂睡眠,可以观察到所有后台线程启动后的结果;只要main()方法不终止,后台进程就会一直存在运行		
		TimeUnit.MILLISECONDS.sleep(175);
	}
}