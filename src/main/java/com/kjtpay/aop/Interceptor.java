package com.kjtpay.aop;

import java.lang.reflect.Method;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: Interceptor
 * @author: caojiaqi
 * @Date: Created in 2019-11-13 10:07
 * @Description： TODO
 */
public interface Interceptor {
	 boolean before(Object proxy, Object target, Method method,Object[] args);
	 void around(Object proxy, Object target, Method method,Object[] args);
	 void after(Object proxy, Object target, Method method,Object[] args);


}
