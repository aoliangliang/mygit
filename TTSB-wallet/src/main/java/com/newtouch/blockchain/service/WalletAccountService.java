package com.newtouch.blockchain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.blockchain.dao.WalletAccountDao;
import com.newtouch.blockchain.entity.WalletAccount;

@Service
public class WalletAccountService {
	@Autowired
	private WalletAccountDao walletAccountDao;

	/**
	 * 根据用户名查询
	 * 
	 * @param username
	 * @return
	 */
	public WalletAccount findByUsername(String username) {
		return walletAccountDao.findByUsername(username);
	}

	public void save(WalletAccount user) {
//		user.setAvatar("http://blockchain.newtouch.com.cn:46083/temp/avatar.jpeg");
		user.setStatus(WalletAccount.STATUS_YES);
		walletAccountDao.insert(user);
	}
	
	public void update(WalletAccount account) {
		walletAccountDao.updateById(account);
	}
	/**
	 * 修改状态
	 * @param account
	 */
	public void updateStatus(WalletAccount account) {
		walletAccountDao.updateStatus(account);
	}

}
