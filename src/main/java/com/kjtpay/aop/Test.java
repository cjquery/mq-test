package com.kjtpay.aop;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: Test
 * @author: caojiaqi
 * @Date: Created in 2019-11-14 11:33
 * @Description： TODO
 */
public class Test {
	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		UserManage userManage = new UserManageImpl();
		//使用userManage的代理来实现日志打印
		UserManage userManageProxy=(UserManage)LogHandler.getInstance(userManage,null);
		userManageProxy.addUser("111","张三");
		UserManage userManageProxyWithInterceptor=(UserManage)LogHandler.getInstance(userManage,"com.kjtpay.aop.MyInterceptor");
		userManageProxyWithInterceptor.addUser("111","张三");
		UserManage userManageProxyWithInterceptor2=(UserManage)LogHandler.getInstance(userManage,"com.kjtpay.aop.MyInterceptor");
		userManageProxyWithInterceptor2.addUser("111","李四");
	}
}
