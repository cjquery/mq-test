package com.kjtpay.aop;

import com.kjtpay.mqtest.MqTestApplicationTests;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: ProxyTest
 * @author: caojiaqi
 * @Date: Created in 2019-11-13 16:59
 * @Description： TODO
 */
public class ProxyTest extends MqTestApplicationTests {
	@Resource
	UserManage userManage;
	private  void saveProxyFile(String... filePath) {
		if (filePath.length != 0) {
			//是否将二进制保存到本地文件中,默认在项目路径下生成 $Proxy0.class 文件
			System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		} else {
			FileOutputStream out = null;
			try {
				byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", userManage.getClass().getInterfaces());
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

	@Test
	public void test1() {
		//要查看动态生成的代理类请在idea vm options里加上 -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true 然后在根目录com\sun\proxy里查看
		//或者使用main方法调用com.kjtpay.aop.Test:
		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		//使用userManage的代理来实现日志打印
		UserManage userManageProxy=(UserManage)LogHandler.getInstance(userManage,null);
		userManageProxy.addUser("111","张三");
		UserManage userManageProxyWithInterceptor=(UserManage)LogHandler.getInstance(userManage,"com.kjtpay.aop.MyInterceptor");
		userManageProxyWithInterceptor.addUser("111","张三");


		UserManage userManageProxyWithInterceptor2=(UserManage)LogHandler.getInstance(userManage,"com.kjtpay.aop.MyInterceptor");
		userManageProxyWithInterceptor2.addUser("111","李四");

	}


}
