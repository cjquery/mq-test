package com.kjtpay.alipractice;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.kjtpay.alipractice
 * @ClassName: ProducerConsumer
 * @author: caojiaqi
 * @Date: Created in 2020/12/14 22:09
 * @Description： TODO
 */
public class ProducerConsumer {
//https://www.cnblogs.com/twoheads/p/10137263.html
	//公司有一位HR和A,B两位面试官，
	// HR每隔1~10秒会获得一份简历，直到获取简历总数量达到100份就不再获得。
	// 面试官一旦面试完就会从HR那边获取简历，直到所有简历都面试反馈完成。
	// 面试官A处理一份简历需要3秒，B处理一份简历需要5秒。
	// AB面试官在处理完简历后需要给HR反馈（随机通过或者不通过）。
	// 请编码模拟本系统

	private static final int MAX_CAPACITY = 100; //阻塞队列容量
	private static BlockingQueue<Integer> blockingQueue= new ArrayBlockingQueue<>(MAX_CAPACITY); //阻塞队列
	private  volatile boolean FLAG = true;
	//简历个数
	private AtomicInteger atomicInteger = new AtomicInteger();
	public void produce() throws InterruptedException {
		while (FLAG){
			//offer方法当队列满，而且放入时间超过设定时间时，返回false;
			//put方法当队列满时，会调用wait方法，put方法会等待一个空的位置出来，然后再执行insert
			//高并发建议使用offer方法
			boolean retvalue = blockingQueue.offer(atomicInteger.incrementAndGet(), 10, TimeUnit.SECONDS);
			if (retvalue
					){
				System.out.println(Thread.currentThread().getName()+"\t 简历投入"+ atomicInteger.get()+"成功"+"已有简历= " + blockingQueue.size());
			}else {
				System.out.println(Thread.currentThread().getName()+"\t 简历投入"+ atomicInteger.get()+"失败"+"已有简历= " + blockingQueue.size());

			}
			TimeUnit.SECONDS.sleep(new Random().nextInt(10)+1);
		}
		System.out.println(Thread.currentThread().getName()+"FLAG变为flase，生产停止");
	}

	public void consume(int time) throws InterruptedException {
		Integer result = null;
		while (true){
			result = blockingQueue.poll(20, TimeUnit.SECONDS);
			if (null==result){
				System.out.println("超20秒没有取到数据，消费者"+Thread.currentThread().getName()+"即将退出");
				return;
			}
			int dealResult =new Random().nextInt(2)+1;
			System.out.println(Thread.currentThread().getName()+"\t 处理"+ result+"成功"+"\t\t"+"资源队列大小= " + blockingQueue.size());
			Thread.sleep(new Random().nextInt(time)+1);
		}

	}
	public void stop() {
		this.FLAG = false;
	}
	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		new Thread(()->{
			try {
				pc.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "HR").start();

		new Thread(()->{
			try {
				pc.consume(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "A").start();

		new Thread(()->{
			try {
				pc.consume(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "B").start();
		try {
			TimeUnit.SECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pc.stop();
	}
}
