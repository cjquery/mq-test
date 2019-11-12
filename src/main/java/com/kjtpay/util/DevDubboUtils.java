package com.kjtpay.util;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

public class DevDubboUtils {

	static RegistryConfig rc = null;

	static {
		try {
			rc = new RegistryConfig();
			//rc.setAddress("zookeeper://10.255.6.154:2181?backup=10.255.6.154:2181,10.255.6.154:2181");// zookeeper注册中心地址
			rc.setAddress("zookeeper://192.168.180.42:2181?backup=192.168.180.43:2181,192.168.180.44:2181");
			rc.setProtocol("zookeeper");// 协议
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <T> T getProvider(Class<T> c,String application) {
		ApplicationConfig ac = new ApplicationConfig();
		ac.setName(application);// 应用名字
		ReferenceConfig<T> rcc = new ReferenceConfig<T>();
		rcc.setTimeout(50000);
		rcc.setRegistry(rc);
		rcc.setApplication(ac);
		rcc.setCheck(true);
		rcc.setInterface(c);
		//rcc.setGroup(group);
		//rcc.setUrl("dubbo://10.255.6.92:28101");
		return rcc.get();
	}
}
