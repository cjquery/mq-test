package com.kjtpay.aop.activeProxyAndInterceptorImplementAop;

import java.lang.reflect.Method;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: Interceptor
 * @author: caojiaqi
 * @Date: Created in 2019-11-13 10:07
 * @Description： AOP使用的主要是动态代理，过滤器使用的主要是函数回调；拦截器使用是反射机制。
 */
public interface Interceptor {
	 boolean before(Object proxy, Object target, Method method,Object[] args);
	 void around(Object proxy, Object target, Method method,Object[] args);
	 void after(Object proxy, Object target, Method method,Object[] args);


}
