package com.kjtpay.lfuAndLru;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * @Package: com.kjtpay.lfuAndLru
 * @ClassName: MinHeap
 * @author: 曹佳琪
 * @Date: Created in 2020/8/11 15:41
 * @Description： 最小堆
 */
public class MinHeap<T extends Comparable>  {
	private ArrayList<T> arr = new ArrayList<>();
	private int capacity;
	public MinHeap(int capacity) {
		this.capacity=capacity;
	}

	/**
	 * 若传入初始数组，需要进行堆化
	 */
	public MinHeap(Collection<T> arr) {
		this.arr.addAll(arr);
		for (int i = parentIndex(arr.size()-1); i >= 0; --i) {
			fixDown(i);
		}
	}

	/**
	 * 交换数组arr[a]和arr[b]的值
	 */
	private void swap(int a, int b) {
		T tmp = arr.get(a);
		arr.set(a, arr.get(b));
		arr.set(b, tmp);
	}

	/**
	 * 得到父节点index
	 */
	private int parentIndex(int index) {
		return (index - 1) >> 1;
	}

	/**
	 * 得到左儿子index
	 */
	private int leftSon(int index) {
		return (index << 1) + 1;
	}

	/**
	 * 得到右儿子index
	 */
	private int rightSon(int index) {
		return (index << 1) + 2;
	}

	/**
	 * 从下往上调整堆
	 */
	private void fixUp(int i) {
		if (i <= 0 || i >= arr.size())
			return;
		// 把子结点往上移动，直到父节点比子节点小
		for (int j = parentIndex(i);
		     j >= 0 && arr.get(j).compareTo(arr.get(i)) > 0;
		     i = j, j = parentIndex(j)) {
			swap(i, j);
		}
	}

	/**
	 * 从上往下调整堆
	 */
	private void fixDown(int i) {
		int son = leftSon(i);
		while (son < arr.size()) {
			// 在左右孩子中找最小的，并与它交换
			if (son + 1 < arr.size() && arr.get(son + 1).compareTo(arr.get(son)) < 0)
				son += 1;
			if (arr.get(i).compareTo(arr.get(son)) <= 0)
				break;
			swap(i, son);
			i = son;
			son = leftSon(i);
		}
	}

	/**
	 * 往堆中压入数值
	 */
	public void push(T val) {
		// 从这个新数据的父结点到根结点已经是一个有序的数列
		// 类似于直接插入排序中将一个数据并入到有序区间中
		if(size()<capacity){
			arr.add(val);
		}else {
			pop();
			arr.add(val);
		}

		fixUp(arr.size()-1);
	}

	/**
	 * 删除堆中值
	 */
	public void remove(T val) {
		int i = arr.indexOf(val);
		swap(i,arr.size()-1);
		arr.remove(arr.size()-1);
		fixDown(0);
	}

	/**
	 * 弹出最小值
	 */
	public T pop() {
		if (arr.isEmpty())
			return null;
		T res = arr.get(0);
		// 将最后一个数据的值赋给根结点
		// 然后再从根结点开始进行一次从上向下的调整
		swap(0, arr.size()-1);
		arr.remove(arr.size()-1);
		fixDown(0);
		return res;
	}

	/**
	 * 查看最小值
	 */
	public T top() {
		if (arr.isEmpty())
			return null;
		return arr.get(0);
	}

	public int size() {
		return arr.size();
	}


	@Override
	public String toString() {
		return "MinHeap: " + arr.toString();
	}
	public static void main(String[] args) {
		MinHeap<Integer> heap = new MinHeap<>(10);
		for (int i = 0; i < 10; ++i) {
			int c = new Random().nextInt() % 10;
			heap.push(c);
			System.out.println(heap.toString());
		}
		while (heap.size() > 0) {
			System.out.println(heap.pop());
		}
	}
}
