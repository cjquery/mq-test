package com.kjtpay.mqtest;

/**
 * @Package: com.kjtpay.mqtest
 * @ClassName: ClassLoaderTest
 * @author: caojiaqi
 * @Date: Created in 2019-04-16 17:45
 * @Description： TODO
 */
public class ClassLoaderTest {
	public String getValue() {
		//自定义加载器可以运行时动态的修改代码生效
		System.out.println("888");
		return "666";
	}
}
