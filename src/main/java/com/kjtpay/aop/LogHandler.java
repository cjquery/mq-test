package com.kjtpay.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: LogHandler
 * @author: caojiaqi
 * @Date: Created in 2019-11-13 16:30
 * @Description： TODO
 */
public class LogHandler implements InvocationHandler {
	private Object targetObject;
	private String interceptorClass;
	private LogHandler(Object targetObject,String interceptorClass){
		this.targetObject=targetObject;
		this.interceptorClass=interceptorClass;

	}
	/*public LogHandler(){

	}*/

	//绑定拦截器
	public  static Object getInstance(Object targetObject,String interceptorClass){
		System.out.println("$Proxy0.class全名: "+Proxy.getProxyClass(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces()));
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
				targetObject.getClass().getInterfaces(),new LogHandler(targetObject,interceptorClass));

	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//动态生成的代理类会调用该方法，然后在该方法里去实际调用代理的方法
		Object result=null;
		if(interceptorClass==null){
			System.out.println("开始打印日志");
			Object invoke = method.invoke(targetObject,args);
			System.out.println("结束打印日志");
			return invoke;
		}
		Interceptor interceptor = (Interceptor)Class.forName(interceptorClass).newInstance();
		if(interceptor.before(proxy,targetObject,method,args)){
			System.out.println("开始打印日志");
			result=method.invoke(targetObject,args);
			System.out.println("结束打印日志");
		}else {
			interceptor.around(proxy,targetObject,method,args);
		}
		interceptor.after(proxy,targetObject,method,args);
		return result;
	}
}
