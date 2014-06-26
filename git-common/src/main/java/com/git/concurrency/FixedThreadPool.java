package com.git.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

	public static void main(String[] args) {
		/**
		 * 有了FixedThreadPool,就可以一次性执行代价高昂的线程分配,因而就可以限制线程的数量了.这可以节省空间,
		 * 因为不用为每个任务都固定的付出创建线程的开销.在事件驱动的系统中,需要线程的事件处理器,通过直接从池中获取线程;
		 * 也不会滥用可获得的资源,因为FixedThreadPool使用的Thread对象的数量是有界的
		 */
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}
