package com.mansur.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorServiceDemo {

	public static void main(String[] args) {
		// ExecutorService executorService = Executors.newFixedThreadPool(5);
		ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		Runnable runnableTask = () -> {
			try {
				System.out.println("Sleeping in runnable task..");
				TimeUnit.MILLISECONDS.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Callable<String> callableTask = () -> {
			System.out.println("Sleeping in callable task..");
			TimeUnit.MILLISECONDS.sleep(3000);
			return "Task's execution";
		};

		List<Callable<String>> callableTasks = new ArrayList<Callable<String>>();
		callableTasks.add(callableTask);
		callableTasks.add(callableTask);
		callableTasks.add(callableTask);

		// invoke tasks
		executorService.execute(runnableTask);

		Future<String> future = executorService.submit(callableTask);

		try {
			String result = executorService.invokeAny(callableTasks);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		List<Future<String>> futures = null;
		try {
			futures = executorService.invokeAll(callableTasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// shutdown
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}

		// read future
		String result = null;
		try {
			String result1 = future.get(200, TimeUnit.MILLISECONDS);
			System.out.println("Result: " + result1);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		for(Future<String> f : futures) {
			try {
				System.out.println(f.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		
		// scheduled executor service 
		ScheduledExecutorService scheduledExecutorService = Executors
				  .newSingleThreadScheduledExecutor();
		
		scheduledExecutorService.scheduleWithFixedDelay(runnableTask, 100, 150, TimeUnit.MILLISECONDS);

	}

}
