package com.git.concurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Runnable是执行工作的独立任务,但是它不返回任何值.如果希望任务在完成时能够返回一个值,那么可以实现Callable接口而不是Runnable接口
 * Java SE5中引入的Callable是一种具有类型参数的泛型,它的类型参数表示的是从call()方法(而不是Run())中返回的值,
 * 并且必须使用ExecutorService.submit()方法来调用它.
 */
class TaskWithResult implements Callable<String> {

	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	// 返回的值类型是Callable<String>泛型的类型,即String类型
	public String call() throws Exception {
		return "result of TaskWithResult " + id;
	}

}

public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			results.add(exec.submit(new TaskWithResult(i)));
		}
		for (Future<String> fs : results)
			try {
				// 使用isDone()方法进行检查Future是否已经完成
				if (fs.isDone())
					System.out.println(fs.get());// 当任务完成时,它具有一个结果,可以调用get()方法来获取该结果
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
			} finally {
				exec.shutdown();
			}
	}
}
