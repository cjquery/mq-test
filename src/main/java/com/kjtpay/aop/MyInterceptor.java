package com.kjtpay.aop;

import java.lang.reflect.Method;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: MyInterceptor
 * @author: caojiaqi
 * @Date: Created in 2019-11-13 16:05
 * @Description： 此拦截器接口中的三个方法before()、after()分别在目标方法执行前、执行后执行，如果before()方法返回false，则执行around()方法
 */
public class MyInterceptor implements Interceptor {
	@Override
	public boolean before(Object proxy, Object target, Method method, Object[] args) {

		if(args[1]!=null && args[1].equals("张三")){
			System.out.println("拦截器开始操作");
			return true;
		}else{
			System.out.println("拦截不是张三的新增");
			return false;
		}

	}

	@Override
	public void around(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("拦截该操作，不执行");

	}

	@Override
	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("拦截器结束操作");

	}
}
