package com.newtouch.blockchain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newtouch.blockchain.dao.WalletNodeDao;
import com.newtouch.blockchain.entity.WalletNode;

@Service
public class WalletNodeService {
	@Autowired
	private WalletNodeDao walletNodeDao;
	
	public void save(WalletNode walletNode) {
		walletNodeDao.insert(walletNode);
	}
	public void update(WalletNode walletNode) {
		walletNodeDao.updateById(walletNode);
	}
	public WalletNode findById(String id) {
		return walletNodeDao.selectById(id);
	}
	public List<WalletNode> findAll() {
		return walletNodeDao.selectAll();
	}
	public PageInfo<WalletNode> page(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<WalletNode> nodes = findAll();
		return new PageInfo<WalletNode>(nodes);
	}
}
