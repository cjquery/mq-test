package com.kjtpay.blockingQueue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.kjtpay.blockingQueue
 * @ClassName: MyBlockingQueue
 * @author: 曹佳琪
 * @Date: Created in 2020/8/7 9:34
 * @Description： 生产者消费者模型 实现一个简单的阻塞队列https://zhuanlan.zhihu.com/p/64156753
 */
public class MyBlockingQueue {
	/** 存放元素的数组 */
	private final Object[] items;

	/** 弹出元素的位置 */
	private int takeIndex;

	/** 插入元素的位置 */
	private int putIndex;

	/** 队列中的元素总数 */
	private AtomicInteger count=new AtomicInteger(0);

	/** 插入锁 */
	private final ReentrantLock putLock = new ReentrantLock();
	/** 队列未满的条件变量 */
	private final Condition notFull = putLock.newCondition();

	/** 弹出锁 */
	private final ReentrantLock takeLock = new ReentrantLock();

	/** 队列非空的条件变量 */
	private final Condition notEmpty = takeLock.newCondition();
	/**
	 * 指定队列大小的构造器
	 *
	 * @param capacity  队列大小
	 */
	public MyBlockingQueue(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException();
		// putIndex, takeIndex和count都会被默认初始化为0
		items = new Object[capacity];
	}
	/**
	 * 将指定元素插入队列
	 *
	 * @param e 待插入的对象
	 */
	public void put(Object e)  throws InterruptedException{
		int c;
		putLock.lock();
		try {
			while (count.get() == items.length) {
				notFull.await();
			}
			// 将对象e放入putIndex指向的位置
			items[putIndex] = e;
			// putIndex向后移一位，如果已到末尾则返回队列开头(位置0)
			if (++putIndex == items.length){
				putIndex = 0;
			}
			c = count.getAndIncrement();
			// 如果在插入后队列仍然没满，则唤醒其他等待插入的线程
			if (c + 1 < items.length){
				notFull.signal();
			}
		}finally {
			putLock.unlock();
		}
		// 如果插入之前队列为空，才唤醒等待弹出元素的线程
		// 为了防止死锁，不能在释放putLock之前获取takeLock
		if (c == 0){
			signalNotEmpty();
		}


	}

	/**
	 * 从队列中弹出一个元素
	 *
	 * @return  被弹出的元素
	 */
	public Object take() throws InterruptedException {
		Object e;
		int c;
		takeLock.lock();
		try {
			while (count.get() == 0) {
				notEmpty.await();
			}
			// 取出takeIndex指向位置中的元素
			// 并将该位置清空
			 e = items[takeIndex];
			items[takeIndex] = null;

			// takeIndex向后移一位，如果已到末尾则返回队列开头(位置0)
			if (++takeIndex == items.length){
				takeIndex = 0;
			}


			// 减少元素总数
			c = count.getAndDecrement();
			// 如果队列在弹出一个元素后仍然非空，则唤醒其他等待队列非空的线程
			if (c - 1 > 0){
				notEmpty.signal();
			}

		}finally {
			takeLock.unlock();
		}
		// 只有在弹出之前队列已满的情况下才唤醒等待插入元素的线程
		// 为了防止死锁，不能在释放takeLock之前获取putLock
		if (c == items.length){
			signalNotFull();
		}
		return e;

	}



	/**
	 * 唤醒等待队列非空条件的线程
	 */
	private void signalNotEmpty() {
		// 为了唤醒等待队列非空条件的线程，需要先获取对应的takeLock
		takeLock.lock();
		try {
			// 唤醒一个等待非空条件的线程
			notEmpty.signal();
		} finally {
			takeLock.unlock();
		}
	}

	/**
	 * 唤醒等待队列未满条件的线程
	 */
	private void signalNotFull() {
		// 为了唤醒等待队列未满条件的线程，需要先获取对应的putLock
		putLock.lock();
		try {
			// 唤醒一个等待队列未满条件的线程
			notFull.signal();
		} finally {
			putLock.unlock();
		}
	}
}
