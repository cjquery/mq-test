package com.kjtpay.lfuAndLru;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.kjtpay.lfuAndLru
 * @ClassName: LFUCache
 * @author: 曹佳琪
 * @Date: Created in 2020/8/11 16:31
 * @Description： 小顶堆+hashmap LFU
 */
public class LFUCache {
	Map<Integer, Node> cache;
	MinHeap<Node> queue;
	int capacity;
	int size;
	int idx = 0;  //使用顺序用来在频次相同时比较时间
	public LFUCache(int capacity) {
		cache = new HashMap<>(capacity);
		if (capacity > 0) {
			queue = new MinHeap<>(capacity);
		}
		this.capacity = capacity;
	}

	public int get(int key) {
		Node node = cache.get(key);
		if (node == null) {
			return -1;
		}
		node.freq++;
		node.idx = idx++;
		queue.remove(node);
		queue.push(node);
		return node.value;
	}

	public void put(int key, int value) {
		if (capacity == 0) {
			return;
		}
		Node node = cache.get(key);
		if (node != null) {
			node.value = value;
			node.freq++;
			node.idx = idx++;
			queue.remove(node);
			queue.push(node);
		} else {
			if (size == capacity) {
				cache.remove(queue.top().key);
				queue.pop();
				size--;
			}
			Node newNode = new Node(key, value, idx++);
			cache.put(key, newNode);
			queue.push(newNode);
			size++;
		}
	}



	class Node implements Comparable<Node> {
		int key;
		int value;
		int freq;//频次
		int idx;
		public Node() {}
		public Node(int key, int value, int idx) {
			this.key = key;
			this.value = value;
			freq = 1;
			this.idx = idx;
		}
		public int compareTo(Node node) {
			int diff = freq - node.freq;
			return diff != 0? diff: idx - node.idx;
		}

		@Override
		public boolean equals(Object obj) {
			return this.key==((Node)obj).key;
		}
	}
	public static void main(String[] as) throws Exception {
		LFUCache cache = new LFUCache(3);
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);

		cache.get(1);
		cache.get(1);

		cache.put(4, 4);
		cache.get(4);
		cache.get(3);
		cache.put(5, 5);
		for (Integer key : cache.cache.keySet()) {
			System.out.println(key);
		}


	}




}
