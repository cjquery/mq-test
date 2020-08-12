package com.kjtpay.example;

import org.junit.Test;

import java.util.Random;

/**
 * @Package: com.kjtpay.example
 * @ClassName: ExampleTest
 * @author: 曹佳琪
 * @Date: Created in 2020/8/10 9:49
 * @Description： 打印随机数组测试
 */
public class ExampleTest {

	/**
	 * 打印随机数组
	 */
	@Test
	public void testRandomPrintArray() {
		int size=10;
		int[] array= new int[size];
		for(int i=0;i<size;i++){
			array[i]=i;
		}
		Random rad=new Random();

		for(int i=0;i<size;i++){
			int value=rad.nextInt(size-i);
			int tem = array[size-i-1];
			array[size-i-1]=array[value];
			array[value] =tem;
		}
		for(int i=0;i<size;i++){
			System.out.println(array[i]);
		}

	}




}
