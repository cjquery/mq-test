package com.kjtpay.aop.staticProxy;

/**
 * @Package: com.kjtpay.aop.staticProxy
 * @ClassName: ProxyRole
 * @author: 曹佳琪
 * @Date: Created in 2020/7/24 17:10
 * @Description： 代理类
 */
public class ProxyRole implements Proxy{
	private Proxy realityRole;
	//传入一个真实角色
	public ProxyRole() {
		realityRole = new ProxyRole();
	}
	@Override
	public void todo() {
		//在真实角色功能运行之前，代理角色做准备工作
		        doBefore();
		        //执行真实角色的功能
		         realityRole.todo();
		        //代理角色的收尾工作
		       doAfter();
	}
	private void doBefore() {
		System.out.println("准备工作");
	}
	private void doAfter() {
		System.out.println("收尾工作");
	}
}
