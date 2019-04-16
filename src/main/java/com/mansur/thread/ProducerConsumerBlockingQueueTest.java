package com.mansur.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerBlockingQueueTest {

	public static void main(String[] args) {

		// Use the ArrayBlockingQueue
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		Thread tbqp = new Thread(new BlockingQueueProducer(queue));
		Thread tbqc = new Thread(new BlockingQueueConsumer(queue));

		tbqp.start();
		tbqc.start();
	}

}

class BlockingQueueProducer implements Runnable {

	private BlockingQueue<Integer> blockingQ = null;
	private Integer num = 0;

	public BlockingQueueProducer(BlockingQueue<Integer> q) {
		super();
		this.blockingQ = q;
	}

	@Override
	public void run() {
		while (true) {
			try {
				produce();
			} catch (InterruptedException e) {
			}
		}
	}

	private void produce() throws InterruptedException {
		blockingQ.put(++num);
		System.out.println("p->" + blockingQ);
		int timeout = (int) Math.random() * 1000;
		TimeUnit.MILLISECONDS.sleep(timeout);
	}

}

class BlockingQueueConsumer implements Runnable {

	private BlockingQueue<Integer> blockingQ = null;

	public BlockingQueueConsumer(BlockingQueue<Integer> q) {
		super();
		this.blockingQ = q;
	}

	@Override
	public void run() {
		while (true) {
			try {
				consume();
			} catch (InterruptedException e) {
			}
		}
	}

	private void consume() throws InterruptedException {
		Integer consumed = blockingQ.take();
		// System.out.println("c->" + blockingQ);
		System.out.println("consumed: " + consumed);
		int timeout = (int) Math.random() * 1000;
		TimeUnit.MILLISECONDS.sleep(timeout);
	}

}
