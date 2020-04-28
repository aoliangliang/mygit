package com.newtouch.blockchain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.blockchain.dao.WalletBcTxRecordDao;
import com.newtouch.blockchain.entity.WalletBcTxRecord;

@Service
public class WalletBcTxRecordService {
	@Autowired
	private WalletBcTxRecordDao walletBcTxRecordDao;
	
	public void save(WalletBcTxRecord record) {
		walletBcTxRecordDao.insert(record);
	}
	
	public Long findMaxTxBlockNumber() {
		return walletBcTxRecordDao.findMaxBlockNumber();
	}
	
	public Long findMinTxBlockNumber() {
		return walletBcTxRecordDao.findMinBlockNumber();
	}
}
