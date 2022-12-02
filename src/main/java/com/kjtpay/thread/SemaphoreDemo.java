package com.kjtpay.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.kjtpay.thread
 * @ClassName: SemaphoreDemo
 * @author: 曹佳琪
 * @Date: Created in 2020/7/8 20:01
 * @Description： semaphore的变体

 */
public class SemaphoreDemo {
	private static AtomicInteger autoInt = new AtomicInteger();

	private static class PrintNum extends Thread {
		private int num;

		public PrintNum(int num) {
			this.num = num;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				if (autoInt.intValue() % 3 == num) {
					printStr();
					autoInt.intValue();
				}
				Thread.yield();
			}
		}

		private void printStr() {
			switch (num) {
				case 0:
					System.out.print("A");
				case 1:
					System.out.print("B");
				default:
					System.out.print("C");
			}
		}
	}

	public static void main(String[] args) {
		PrintNum printA = new PrintNum(0);
		PrintNum printB = new PrintNum(1);
		PrintNum printC = new PrintNum(2);
		printA.start();
		printB.start();
		printC.start();
	}


}
