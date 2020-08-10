package com.kjtpay.blockingQueue;

/**
 * @Package: com.kjtpay.blockingQueue
 * @ClassName: ProducerConsumer_V3
 * @author: 曹佳琪
 * @Date: Created in 2020/8/7 17:12
 * @Description： TODO
 */
public class ProducerConsumer_V3 {
	public static void main(String[] args) {
		ShareDataV3 v3 = new ShareDataV3();
		new Thread(()->{
			try {
				v3.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "AAA").start();

		new Thread(()->{
			try {
				v3.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "BBB").start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		v3.stop();
	}
}
