package com.mansur.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerDemo {
	private static List<Integer> sharedList = new ArrayList<Integer>();

	public static void main(String[] args) {
		Thread tp = new Thread(new Producer(sharedList));
		Thread tc = new Thread(new Consumer(sharedList));

		tp.start();
		tc.start();
	}

}

class Producer implements Runnable {
	private List<Integer> sharedList = null;
	private int num = 0;
	private static final int MAX_LIMIT = 5;

	public Producer(List<Integer> sharedList) {
		this.sharedList = sharedList;
	}

	@Override
	public void run() {
		while (true) {
			try { produce(); } catch (InterruptedException e) {}
		}
	}

	private void produce() throws InterruptedException {
		synchronized (sharedList) {
			while (sharedList.size() == MAX_LIMIT) {
				System.out.println("Share list is full, waiting for consumer to consume..");
				sharedList.wait();
			}
		}

		synchronized (sharedList) {
			++num;
			// System.out.println("Producer produced: " + num);
			sharedList.add(num);
			System.out.println(sharedList);
			TimeUnit.MILLISECONDS.sleep(100);
			sharedList.notify();
		}
	}

}

class Consumer implements Runnable {
	private List<Integer> sharedList = null;

	public Consumer(List<Integer> sharedList) {
		this.sharedList = sharedList;
	}

	@Override
	public void run() {
		while (true) {
			try { consume(); } catch (InterruptedException e) {}
		}
	}

	private void consume() throws InterruptedException {
		synchronized (sharedList) {
			while (sharedList.isEmpty()) {
				System.out.println("Share list is empty, waiting for the producer to produce numbers..");
				sharedList.wait();
			}
		}

		synchronized (sharedList) {
			@SuppressWarnings("unused")
			int consumed = sharedList.remove(0);
			// System.out.println("Consumer consumed: " + consumed);
			System.out.println(sharedList);
			TimeUnit.MILLISECONDS.sleep(100);
			sharedList.notify();

		}
	}
}
