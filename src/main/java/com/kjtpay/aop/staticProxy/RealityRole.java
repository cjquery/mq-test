package com.kjtpay.aop.staticProxy;

/**
 * @Package: com.kjtpay.aop.staticProxy
 * @ClassName: RealityRole
 * @author: 曹佳琪
 * @Date: Created in 2020/7/24 17:09
 * @Description： 被代理类
 */
public class RealityRole implements Proxy{
	@Override
	public void todo() {
		System.out.println("真实角色的功能");
	}
}
