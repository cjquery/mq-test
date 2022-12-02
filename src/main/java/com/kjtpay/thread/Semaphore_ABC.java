package com.kjtpay.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Package: com.kjtpay.thread
 * @ClassName: Semaphore_ABC
 * @author: 曹佳琪
 * @Date: Created in 2020/7/8 16:28
 * @Description： 信号量实现
 */
public class Semaphore_ABC {
	// 以A开始的信号量,初始信号量数量为1
	private static Semaphore A = new Semaphore(1);
	// B、C信号量,A完成后开始,初始信号数量为0
	private static Semaphore B = new Semaphore(0);
	private static Semaphore C = new Semaphore(0);

	private class RunnableA implements Runnable {
		@Override
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					A.acquire();// A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
					System.out.print("线程1---A");
					B.release();// B释放信号，B信号量加1（初始为0），此时可以获取B信号量
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class RunnableB implements Runnable {
		@Override
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					B.acquire();
					System.out.print("线程2---B");
					C.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class RunnableC implements Runnable {
		@Override
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					C.acquire();
					System.out.println("线程3---C");
					A.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Semaphore_ABC semaphore_ABC = new Semaphore_ABC();
		ExecutorService service = Executors.newFixedThreadPool(3);

		service.execute(semaphore_ABC.new RunnableC());
		service.execute(semaphore_ABC.new RunnableB());
		service.execute(semaphore_ABC.new RunnableA());

		service.shutdown();
/*
		new Thread(semaphore_ABC.new RunnableA()).start();
		new Thread(semaphore_ABC.new RunnableB()).start();
		new Thread(semaphore_ABC.new RunnableC()).start();*/
	}

}


