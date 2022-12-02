package com.kjtpay.example;

import com.kjtpay.stack.ArrayStack;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Package: com.kjtpay.example
 * @ClassName: TreeTest
 * @author: 曹佳琪
 * @Date: Created in 2020/8/11 14:51
 * @Description： 树的遍历和求最远的两个端点
 */
public class TreeTest {
	class Struct {
		int data;
		Struct left;
		Struct right;
		int nMaxLeft;
		int nMaxRight;
		Struct(int data,Struct left,Struct right){
			this.data=data;
			this.left=left;
			this.right=right;
		}
	}
	//递归法
	public int[] getMaxDistance(Struct root)  {
		int[] result = new int[2];  //result[0]存储节点深度，result[1]存储最远距离
		if (root == null) {
			return new int[]{-1, -1}; //因为如果只有一个节点的时候，nMaxDepth是0(-1+1)，nMaxDistance也是0(-1+-1+2)
		}

		int[] a = getMaxDistance(root.left);
		int[] b = getMaxDistance(root.right);

		result[0] = Math.max(a[0], b[0]) + 1;
		result[1] = Math.max(Math.max(a[1], b[1]), a[0]+b[0]+2);
		return result;
	}
	//遍历法
	public int findMaxLength2(Struct root) {
		int tMaxLength=0;
		ArrayStack<Struct> s = new ArrayStack<>();
		Struct p=root;
		Struct have_visited=null;
		//记录上次访问的结点，主要是用在：判定根结点是
		//否能访问。如果根结点的右孩子是刚访问的，那么就能访问根结点了。
		while(p!=null||!s.isEmpty()) {
			//把最左分支压入栈，类似于中序遍历
			while(p!=null) {
				s.push(p);
				p=p.left;
			}
			p=s.top();
			//如果右子树是空，那么后序遍历就是中序遍历
			//如果如果上次访问的是右结点，那么可以访问根结点
			if(p.right==null||have_visited==p.right) {
				//以下是求最大距离的代码，不属于后序遍历
				if(p.left!=null)
				{
					p.nMaxLeft=p.left.nMaxLeft+1;
					if(p.left.nMaxRight+1>p.nMaxLeft)
						p.nMaxLeft=p.left.nMaxRight+1;
				}
				if(p.right!=null)
				{
					p.nMaxRight=p.right.nMaxRight+1;
					if(p.right.nMaxLeft+1>p.nMaxRight)
						p.nMaxRight=p.right.nMaxLeft+1;
				}
				if((root.nMaxLeft+root.nMaxRight)>tMaxLength)
					tMaxLength=root.nMaxLeft+root.nMaxRight;
				//*************************************结束
				s.pop();
				have_visited=p;
				p=null;
			} else {
				p=p.right; //指向右子树，为下次循环，压栈做准备。
			}

		}
		return tMaxLength;
	}
	//https://www.cnblogs.com/bigsai/p/11393609.html  树遍历
	public void cengxu(Struct t) {//层序遍历
		Queue<Struct> q1 = new ArrayDeque<Struct>();
		if (t == null)
			return;
		if (t != null) {
			q1.add(t);
		}
		while (!q1.isEmpty()) {
			Struct t1 = q1.poll();
			if (t1.left != null)
				q1.add(t1.left);
			if (t1.right != null)
				q1.add(t1.right);
			System.out.print(t1.data + " ");
		}

	}
	// 前序递归 前序遍历：根结点 ---> 左子树 ---> 右子树
	public void qianxu(Struct t) {
		if (t != null) {
			System.out.print(t.data + " ");// 当前节点
			qianxu(t.left);
			qianxu(t.right);
		}

	}
	// 中序遍历 中序遍历：左子树---> 根结点 ---> 右子树
	public void zhongxu(Struct t) {
		if (t != null) {
			zhongxu(t.left);
			System.out.print(t.data + " ");// 访问完左节点访问当前节点
			zhongxu(t.right);
		}

	}
	// 后序遍历 后序遍历：左子树 ---> 右子树 ---> 根结点
	public void houxu(Struct t){
		if (t != null) {
			houxu(t.left);
			houxu(t.right);
			System.out.print(t.data + " "); // 访问玩左右访问当前节点
		}

	}
	//中序遍历结合前序或后序遍历能还原二叉树，但前序+后序只能明确父子关系无法得到唯一的二叉树https://www.cnblogs.com/henuliulei/p/10075415.html
	/**
	 * 求一棵二叉树中相距最远的两个节点之间的距离
	 * 计算一个二叉树的最大距离有两个情况:
	 * 情况A: 路径经过左子树的最深节点，通过根节点，再到右子树的最深节点。
	 * 情况B: 路径不穿过根节点，而是左子树或右子树的最大距离路径，取其大者。
	 * https://www.jianshu.com/p/a23dafdb61f1
	 * 还可以使用遍历法查找每个节点出发的最大距离
	 * 递归就是一种遍历，所有递归写法都能写成普通遍历。人们用递归的原因是递归比较好写，好理解。
	 * 执行速度上递归一般比不过普通的遍历
	 */
	@Test
	public void testTreeMaxDistance() {

		//情况A对应的数据
		Struct p9 = new Struct(9,null,null);
		Struct p8 = new Struct(8,null,null);
		Struct p7 = new Struct(7,null,null);
		Struct p6 = new Struct(6,null,p9);
		Struct p5 = new Struct(5,null,null);
		Struct p4 = new Struct(4,p8,null);
		Struct p3 = new Struct(3,p6,p7);
		Struct p2 = new Struct(2,p4,p5);
		Struct p1 = new Struct(1,p2,p3);
		//层序遍历
		System.out.println("层序遍历");
		cengxu(p1);
		System.out.println();
		System.out.println("前序遍历");
		qianxu(p1);
		System.out.println();
		System.out.println("中序遍历");
		zhongxu(p1);
		System.out.println();
		System.out.println("后序遍历");
		houxu(p1);
		int[] result = getMaxDistance(p1);
		System.out.println("最大深度为："+result[0]+"最远距离为："+result[1]);
		int maxLength1 = findMaxLength2(p1);
		System.out.println("最远距离为-方式1："+maxLength1);


		//情况B对应的数据
		Struct n6 = new Struct(6,null,null);
		Struct n5 = new Struct(5,null,null);
		Struct n4 = new Struct(4,null,n6);
		Struct n3 = new Struct(3,n5,null);
		Struct n2 = new Struct(2,n3,n4);
		Struct n1 = new Struct(1,n2,null);
		int[] result2 = getMaxDistance(n1);
		System.out.println("最大深度为："+result2[0]+"最远距离为："+result2[1]);

		//方法二
		int maxLength2 = findMaxLength2(n1);
		System.out.println("最远距离为-方式2："+maxLength2);



	}

}
