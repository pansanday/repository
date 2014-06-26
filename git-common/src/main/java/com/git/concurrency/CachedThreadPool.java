package com.git.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 执行器Executor将管理Thread对象,从而简化了编程;Executor在客户端和任务执行之间提供了一个间接层;与客户端直接执行任务不同,
 * 这个中介对象将执行任务;Executor允许你管理异步任务的执行,而无须显式的管理线程的生命周期
 */
public class CachedThreadPool {

	public static void main(String[] args) {
		/**
		 * 使用静态的Executor方法创建ExecutorService对象,无须显式的start;单个的Executor被用来创建和管理系统中所有的任务
		 * CachedThreadPool在程序执行过程中通常会创建与所需数量相同的线程,然后在它回收旧线程时停止创建新线程
		 */
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		/**
		 * 调用shutdown()方法可以防止新的任务被提交给这个Executor;当前线程(本例中,即驱动main()的线程)
		 * 将继续运行在shutdown()被调用之前提交的所有任务.这个线程将在Executor中的所有任务完成之后尽快退出;
		 * 如果注释掉shutdown,会看到Console中的Terminate一直未终止,仿佛在等待着什么
		 */
		exec.shutdown();
	}
}
