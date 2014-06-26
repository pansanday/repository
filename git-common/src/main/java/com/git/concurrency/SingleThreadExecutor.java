package com.git.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingleThreadExecutor就像是线程数量为1的FixedThreadPool.如果向SingleThreadExecutor提交了多个任务,
 * 那么这些任务将排队,每个任务都会在下一个任务开始之前运行结束,所有的任务将使用相同的线程;每个任务都是
 * 按照它们所提交的顺序,并且是在下一个任务开始之前完成的.SingleThreadExecutor会序列化所有提交给它的任务,
 * 并维护它自己的悬挂任务队列
 */
public class SingleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}
