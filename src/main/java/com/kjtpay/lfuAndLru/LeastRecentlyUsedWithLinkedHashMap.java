package com.kjtpay.lfuAndLru;

import java.util.LinkedHashMap;

/**
 * @Package: com.kjtpay.lfuAndLru
 * @ClassName: LeastRecentlyUsedWithLinkedHashMap
 * @author: 曹佳琪
 * @Date: Created in 2020/8/11 15:49
 * @Description： LRU实现  双向链表 哈希表（linkedhashmap）
 * LRU是最近最少使用页面置换算法(Least Recently Used),也就是首先淘汰最长时间未被使用的页面!
 */
public class LeastRecentlyUsedWithLinkedHashMap {

	/**
	 * 使用数组+双向链表可以使用LRU算法，并且可以达到O(1)复杂度
	 */
	private Object[] myLinkedHashMap = null; //hash数组
	private int maxSize;
	private int currSize;
	entry head = null; // 双向链表的头结点

	class entry {
		Object key; //key值，可以用于计算存于哪个脚标
		Object value; //对应数组里面存储的值
		int hash;  //hash(key)可以实现散列数组
		entry next = null; //当不同的key，hash值相同时，会以单链表的形式下挂在该结点下面
		entry before = null; //before和after实现双向链表，来维护数据的访问顺序
		entry after = null;
	}

	public LeastRecentlyUsedWithLinkedHashMap(int maxSize) {
		// 初始化一个头结点
		this.maxSize = getMaxSize(maxSize); //hash数组大小必须为2的倍数
		this.currSize = 0;
		myLinkedHashMap = new Object[this.maxSize]; //申请的数组大小必须是2的倍数
		head = new entry();
		head.key = 0;
		head.value = 0;
		head.hash = head.key.hashCode();
		head.next = head.before = head.after = null;
	}

	public int getMaxSize(int maxSize) {
		// 获取大于当前大小，并且最接近2倍数的值
		if (maxSize == 1){
			return 2;
		}
		int capacity = 1;
		while (capacity < maxSize)
			capacity = capacity << 1;
		return capacity;
	}

	/**
	 * 添加一个数据步骤：
	 * -》输入key和value值
	 * -》根据key计算出hash值，然后 K = hash & (maxSize-1)，计算出应该插入的脚标K值
	 * -》申请一个新的结点，将key和value进行赋值
	 * -》判断K脚标的结点上是否存在数据，如果存在数据，遍历该单链表，看是否存在相同的key值，如果
	 * 存在，替换掉原有结点，如果没有相同的key值，插入到链表的头部。
	 * -》使用双向链表维护插入的顺序
	 */
	public Boolean put(Integer key, Object value) {
		int index = key.hashCode() & (this.maxSize - 1); //计算待插入的脚标
		//新增一个结点
		entry indexEntry = new entry();
		indexEntry.key = key;
		indexEntry.value = value;
		indexEntry.hash = key.hashCode();

		if (myLinkedHashMap[index] != null) {
			//说明该结点已经有值,存在单链表
			entry node = null;
			if (myLinkedHashMap[index] instanceof entry) {
				node = (entry) myLinkedHashMap[index];
			} else {
				return false;
			}
			for (; node != null; node = node.next) {
				// 遍历结点所在的单链表
				if (node.key.equals(key)) {
					/**
					 * 表示找到了存在key值的结点，这个时候需要替换该结点的值，并且在
					 * 双向链表当中删除该结点，并且将该结点添加至双向链表的尾部。
					 */
					node.value = value;
					removeDoubleLinkedListElem(node);
					addElem(node);
					return true;
				} else {
					continue;
				}
			}
			if (node == null) {
				/**
				 * 说明该结点所在的单链表当中不存在与key相等的结点，则需要将该结点插至
				 * 该单链表的第一个结点，并且更新双向链表。
				 */
				if (currSize == this.maxSize){
					//双向链表已经处于full状态，则需要移除第一个结点
					removeLinkedListElem(head.after);
					removeDoubleLinkedListElem(head.after);
				}
				indexEntry.next = (entry)myLinkedHashMap[index]; //将新新增的结点插入到单链表的头部
				myLinkedHashMap[index] = indexEntry; //将新增结点放置在hash数组上
				addElem(indexEntry); //维护双向链表
				return true;
			}
		} else {
			//说明index位置上不存在值，那么直接插入即可，并且维护双向链表
			if (currSize == this.maxSize){
				//双向链表已经处于full状态，则需要移除第一个结点
				removeLinkedListElem(head.after);
				removeDoubleLinkedListElem(head.after);
			}
			myLinkedHashMap[index] = indexEntry;
			addElem(indexEntry); //维护双向链表
			return true;
		}

		return true;
	}

	public Object removeDoubleLinkedListElem(entry entry) {
		//在双向链表当中删除该结点
		Object returnObject = entry.value;
		entry.before.after = entry.after;
		entry.after.before = entry.before;
		entry.before = entry.after = null;
		this.currSize--;
		return returnObject;
	}

	public Object removeLinkedListElem(entry entry){
		int index = entry.hash & (this.maxSize - 1); //计算出该结点所在散列数组的脚标
		entry currNode = null; //记录脚标所在结点的位置
		entry delNode = null; //记录单链表待删除的结点
		entry preNode = null; //记录单链表待删除结点的前一个结点位置
		Object returnObject = null;
		if (myLinkedHashMap[index] instanceof  entry){
			currNode = (entry)myLinkedHashMap[index];
		}else{return -1;}

		if (currNode.next == null && currNode.key.equals(entry.key)){
			returnObject = currNode.value;
			myLinkedHashMap[index] = null; //如果对应脚标的位置只有一个结点，直接置为空
			return returnObject;
		}else{
			for (preNode = currNode,delNode = currNode.next;delNode != null;preNode = delNode,delNode = delNode.next){
				if (delNode.key.equals(entry.key)){
					//说明已经找到对应的结点
					returnObject = delNode.value;
					if (delNode.next != null){
						//表示该结点不处于单链表的尾部
						preNode.next = delNode.next;
						delNode.next = null;
						return returnObject;
					}else{
						preNode.next = null;
						return returnObject;
					}
				}else{continue;}
			}
			return -1;
		}
	}

	public Boolean addElem(entry entry) {
		//在双向链表的尾部添加元素结点
		if (currSize == 0){
			// 表示是空的双向链表
			entry.before = entry.after = head;
			head.before = head.after = entry;
			this.currSize++;
			return true;
		}
		entry.after = head;
		entry.before = head.before;
		head.before.after = entry;
		head.before = entry;
		this.currSize++;
		return true;
	}

	/**
	 * 查找一个数据步骤
	 * -》根据key值计算hashcode，然后 K = hash & (maxSize-1)，计算出脚标
	 * -》如果该结点是一个单链表，就从头结点开始遍历，查找与key相同的结点，如果没找到返回-1
	 * 如果找到了，在双向链表当中删除该结点，然后将该结点插到双向链表的尾部
	 */

	public Object get(Object key) {
		int index = key.hashCode() & (this.maxSize - 1);
		entry node = null;
		if (myLinkedHashMap[index] instanceof entry) {
			node = (entry) myLinkedHashMap[index];
		} else {
			return -1; //表示未查找到数据
		}

		if (node.next != null){
			//表示该结点存在单链表，需要进行遍历
			for (;node != null;node = node.next){
				if (node.key.equals(key)){
					removeDoubleLinkedListElem(node);
					addElem(node);
					return node.value;
				}else{
					continue;
				}
			}
			if (node == null){
				//说明未存在key
				return -1;
			}
		}else{
			if (node.key.equals(key)){
				removeDoubleLinkedListElem(node);
				addElem(node);
				return node.value;
			} else{return -1;}
		}
		return -1;
	}

	//实现toString功能，方便测试
	public String toString(){
		StringBuffer sb = new StringBuffer();
		if (currSize == 0){
			return "";
		}
		entry currNode = head.after;
		for (;currNode != head;currNode = currNode.after){
			if (currNode.after != head){
				sb.append(currNode.value);
				sb.append(",");
			}else {
				sb.append(currNode.value);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		LeastRecentlyUsedWithLinkedHashMap lrucache = new LeastRecentlyUsedWithLinkedHashMap(3);
		lrucache.put(1,"zhangsan");
		lrucache.put(2,"lisi");
		lrucache.put(3,"wangwu");
		lrucache.get(1);
		System.out.println(lrucache.toString());

		//使用自带jdk LinkedHashMap实现
		LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<Object, Object>(1,0.75f,true);
		objectObjectLinkedHashMap.put(1,"zhangsan");
		objectObjectLinkedHashMap.put(2,"lisi");
		objectObjectLinkedHashMap.put(3,"wangwu");
		objectObjectLinkedHashMap.get(1);
		objectObjectLinkedHashMap.get(2);
		System.out.println(objectObjectLinkedHashMap.toString());
	}
}
