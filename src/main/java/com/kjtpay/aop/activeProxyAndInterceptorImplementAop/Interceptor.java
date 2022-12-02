package com.kjtpay.aop.activeProxyAndInterceptorImplementAop;

import java.lang.reflect.Method;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: Interceptor
 * @author: caojiaqi
 * @Date: Created in 2019-11-13 10:07
 * @Description： AOP使用的主要是动态代理，拦截器使用用责任链和适配器的设计模式来实现。
 * 过滤器使用的主要是函数回调；IOC主要是反射 DispatcherServlet
 *  拦截器只对action负责，作用层面一般位于Controller层
 *  Spring AOP主要是拦截对Spring管理的Bean的访问，一般作用与Service层,也可对controller层，过滤器一般对静态资源进行处理
 * 本文模拟了spring aop+拦截器的实现
 */
public interface Interceptor {
	 boolean before(Object proxy, Object target, Method method,Object[] args);
	 void around(Object proxy, Object target, Method method,Object[] args);
	 void after(Object proxy, Object target, Method method,Object[] args);


}
