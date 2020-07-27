package com.kjtpay.aop.activeProxyAndInterceptorImplementAop;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: UserManage
 * @author: caojiaqi
 * @Date: Created in 2019-11-13 16:27
 * @Description： TODO
 */
public interface UserManage {
	void addUser(String userId, String userName);
	void delUser(String userId);
}
