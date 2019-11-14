package com.kjtpay.aop;

import org.springframework.stereotype.Service;

/**
 * @Package: com.kjtpay.aop
 * @ClassName: UserManageImpl
 * @author: caojiaqi
 * @Date: Created in 2019-11-13 16:29
 * @Description： TODO
 */
@Service("userManage")
public class UserManageImpl implements UserManage {
	@Override
	public void addUser(String userId, String userName) {
		System.out.println("UserManagerImpl.addUser");
	}

	@Override
	public void delUser(String userId) {
		System.out.println("UserManagerImpl.delUser");
	}
}
