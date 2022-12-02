package com.kjtpay.aop.activeProxyAndInterceptorImplementAop;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: Test
 * @author: caojiaqi
 * @Date: Created in 2019-11-14 11:33
 * @Description： TODO
 */
public class Test {
	private static void saveProxyFile(String... filePath) {
		if (filePath.length != 0) {
			//是否将二进制保存到本地文件中,默认在项目路径下生成 $Proxy0.class 文件
			System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		} else {
			FileOutputStream out = null;
			try {
				byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", UserManage.class.getInterfaces());
				out = new FileOutputStream(filePath[0] + "$Proxy0.class");
				out.write(classFile);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.flush();
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {

		//saveProxyFile();自定义动态代理类保存路径
		//要查看动态生成的代理类请在idea vm options里加上 -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true 然后在根目录com\sun\proxy里查看
		// 或者设置System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		//使用userManage的代理来实现日志打印
		UserManage userManage = new UserManageImpl();
		//使用userManage的代理来实现日志打印
		UserManage userManageProxy=(UserManage)LogHandler.getInstance(userManage,null);
		userManageProxy.addUser("111","张三");
		UserManage userManageProxyWithInterceptor=(UserManage)LogHandler.getInstance(userManage,"com.kjtpay.aop.activeProxyAndInterceptorImplementAop.MyInterceptor");
		userManageProxyWithInterceptor.addUser("111","张三");
		UserManage userManageProxyWithInterceptor2=(UserManage)LogHandler.getInstance(userManage,"com.kjtpay.aop.activeProxyAndInterceptorImplementAop.MyInterceptor");
		userManageProxyWithInterceptor2.addUser("111","李四");
	}
}
