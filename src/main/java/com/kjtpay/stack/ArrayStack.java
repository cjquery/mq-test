package com.kjtpay.stack;

/**
 * @Package: com.kjtpay.stack
 * @ClassName: ArrayStack
 * @author: 曹佳琪
 * @Date: Created in 2020/8/10 10:20
 * @Description： 用链表实现线程安全的栈
 */
public class ArrayStack<Item> {
	private Node first;//栈顶
	private int N;

	private class Node {
		Item item;     //泛型，这相当于占位符
		Node next;    //这里的next变量存储的是下一个结点，既然是下一个结点自然就是节//点类型
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public Item top() {
		return first.item;
	}

	public synchronized void push(Item item) {
		Node oldfirst = first;     //保存的目的在于要给新结点的next赋值
		Node newnode = new Node(); //
		first = newnode;           //这两句可以合并为first = new Node();
		newnode.next = oldfirst;
		newnode.item = item;
		N++;         //别忘了这句
	}


	public synchronized Item pop() {
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

	public static void main(String[] args) {
		ArrayStack<String> s = new ArrayStack<String>();
		s.push("AAA");
		s.push("BBB");
		s.push("CCC");
		while (!s.isEmpty()) {
			System.out.println(s.pop());
		}
	}

}

