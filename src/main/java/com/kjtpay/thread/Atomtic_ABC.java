package com.kjtpay.thread;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.kjtpay.thread
 * @ClassName: Atomtic_ABC
 * @author: 曹佳琪
 * @Date: Created in 2020/7/8 16:18
 * @Description： 使用原子类实现，缺点时锁竞争激烈。推荐使用多线程通信方式实现
 * 常见多线程通信方式：
 * Synchronized + wait() + notify()
 * Lock + await() + signal()
 * semaphore
 * 基于semaphore的变体

 */
public class Atomtic_ABC {
	private AtomicInteger ai = new AtomicInteger(0);
	private static final int MAX_SYC_VALUE = 3 * 10;

	private class RunnableA implements Runnable {
		public void run() {
			while (ai.get() < MAX_SYC_VALUE-1) {
				if (ai.get() % 3 == 0) {
					System.out.print("线程1---A");
					ai.getAndIncrement();
				}
			}

		}
	}

	private class RunnableB implements Runnable {
		public void run() {
			while (ai.get() < MAX_SYC_VALUE) {
				if (ai.get() % 3 == 1) {
					System.out.print("线程2---B");
					ai.getAndIncrement();
				}
			}

		}
	}

	private class RunnableC implements Runnable {
		public void run() {
			while (ai.get() < MAX_SYC_VALUE) {
				if (ai.get() % 3 == 2) {
					System.out.println("线程3---C");
					ai.getAndIncrement();
				}
			}

		}
	}


	public static void main(String[] args) {

		Atomtic_ABC atomic_ABC = new Atomtic_ABC();
		/*ExecutorService service = Executors.newFixedThreadPool(3);

		service.execute(atomic_ABC.new RunnableA());
		service.execute(atomic_ABC.new RunnableB());
		service.execute(atomic_ABC.new RunnableC());

		service.shutdown();*/
		new Thread(atomic_ABC.new RunnableA()).start();
		new Thread(atomic_ABC.new RunnableB()).start();
		new Thread(atomic_ABC.new RunnableC()).start();
	}
}
