package com.mansur.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(50);
		for (int i = 0; i < 10; i++) {
			Runnable worker = new MultiplicationTableGeneratorThread(i + 1);
			executorService.execute(worker);
		}
		executorService.shutdown();

		// keep on waiting executor service is terminated for all requests
		while (!executorService.isTerminated()) {
		}
		System.out.println("All requests completed successfully");
	}

}

class MultiplicationTableGeneratorThread implements Runnable {

	private Integer number;

	public MultiplicationTableGeneratorThread(Integer number) {
		super();
		this.number = number;
	}

	@Override
	public void run() {
		System.out.println(String.format("-- %d Table --", new Object[] { number }));
		for (int i = 1; i <= 10; i++) {
			System.out.println(String.format("%d x %d = %d", new Object[] { number, i, number * i }));
		}
		System.out.println();
	}

}
