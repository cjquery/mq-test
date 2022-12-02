package com.kjtpay.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.kjtpay.blockingQueue
 * @ClassName: ShareDataV3
 * @author: 曹佳琪
 * @Date: Created in 2020/8/7 17:11
 * @Description： 资源类--使用阻塞队列实现生产者-消费者 https://www.jianshu.com/p/ab013a4d5878
 */
public class ShareDataV3 {
	private static final int MAX_CAPACITY = 10; //阻塞队列容量
	private static BlockingQueue<Integer> blockingQueue= new ArrayBlockingQueue<>(MAX_CAPACITY); //阻塞队列
	private  volatile boolean FLAG = true;
	private AtomicInteger atomicInteger = new AtomicInteger();

	public void produce() throws InterruptedException {
		while (FLAG){
			//offer方法当队列满，而且放入时间超过设定时间时，返回false;
			//put方法当队列满时，会调用wait方法，put方法会等待一个空的位置出来，然后再执行insert
			//高并发建议使用offer方法
			boolean retvalue = blockingQueue.offer(atomicInteger.incrementAndGet(), 2, TimeUnit.SECONDS);
			if (retvalue==true){
				System.out.println(Thread.currentThread().getName()+"\t 插入队列"+ atomicInteger.get()+"成功"+"资源队列大小= " + blockingQueue.size());
			}else {
				System.out.println(Thread.currentThread().getName()+"\t 插入队列"+ atomicInteger.get()+"失败"+"资源队列大小= " + blockingQueue.size());

			}
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(Thread.currentThread().getName()+"FLAG变为flase，生产停止");
	}

	public void consume() throws InterruptedException {
		Integer result = null;
		while (true){
			result = blockingQueue.poll(2, TimeUnit.SECONDS);
			if (null==result){
				System.out.println("超过两秒没有取道数据，消费者即将退出");
				return;
			}
			System.out.println(Thread.currentThread().getName()+"\t 消费"+ result+"成功"+"\t\t"+"资源队列大小= " + blockingQueue.size());
			Thread.sleep(1500);
		}

	}

	public void stop() {
		this.FLAG = false;
	}
}
