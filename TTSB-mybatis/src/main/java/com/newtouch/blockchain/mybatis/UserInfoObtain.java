package com.newtouch.blockchain.mybatis;

/**
 * 用户基本信息获取
 * @Package com.ghw.fs.base
 * @ClassName: UserInfoObtain
 */
public interface UserInfoObtain {
	
	String BEAN_ID = "userInfoObtain";
	
	/**
	 * 默认用户信息 id
	 */
	String DEF_USER = "system";
	
	/**
	 * 获取用户信息 id
	 * @return String
	 */
	default public String userId() {
		return DEF_USER;
	}
	
	/**
	 * 获取用户名
	 * @return String
	 */
	default public String userName() {
		return DEF_USER;
	}
}
