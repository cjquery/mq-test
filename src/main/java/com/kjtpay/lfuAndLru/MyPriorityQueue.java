package com.kjtpay.lfuAndLru;

/**
 * @Package: com.kjtpay.lfuAndLru
 * @ClassName: MyPriorityQueue
 * @author: 曹佳琪
 * @Date: Created in 2020/8/11 17:37
 * @Description： 小顶堆实现优先队列
 */
public class MyPriorityQueue<T extends Comparable>{

	private MinHeap<T> minHeap;

	public MyPriorityQueue() {
		this(20);
	}

	/**
	 * 初始化优先队列
	 *
	 * @param capacity
	 */
	public MyPriorityQueue(int capacity) {
		this.minHeap = new MinHeap<T>(10);
	}
	/**
	 * 返回优先队列的大小
	 *
	 * @return
	 */
	public int Size() {
		return minHeap.size();
	}
	public void enqueue(T x) {
		//根据元素大小插入在单链表适当位置
		minHeap.push(x);
	}

	/**
	 * 出队，返回队头元素，若队列空返回null
	 */
	public T dequeue() {
		//返回队头元素，删除队头结点
		return minHeap.pop();
	}
}




