package com.newtouch.blockchain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newtouch.blockchain.dao.WalletCurrencyDao;
import com.newtouch.blockchain.entity.WalletCurrency;

@Service
public class WalletCurrencyService {
	@Autowired
	private WalletCurrencyDao walletCurrencyDao;
	
	public List<WalletCurrency> findCurrencyExcludePoint() {
		WalletCurrency param = new WalletCurrency();
		List<WalletCurrency> walletCurrencies = findByEntity(param);
		return walletCurrencies;
	}
	public List<WalletCurrency> findByEntity(WalletCurrency currency) {
		return walletCurrencyDao.findByEntity(currency);
	}
	
	public PageInfo<WalletCurrency> pageCurrencyExcludePoint(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<WalletCurrency> walletCurrencies = findCurrencyExcludePoint();
		return new PageInfo<WalletCurrency>(walletCurrencies);
	}
	
	public WalletCurrency findById(String id) {
		WalletCurrency currency = walletCurrencyDao.selectById(id);
		return currency;
	}
	
	public List<WalletCurrency> findByStatus(String status) {
		return walletCurrencyDao.findByStatus(status);
	}
	
	public List<WalletCurrency> findByAccount(String accountId) {
		return walletCurrencyDao.findByAccount(accountId);
	}
	public PageInfo<WalletCurrency> pageByAccount(String accountId,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<WalletCurrency> list = findByAccount(accountId);
		return new PageInfo<WalletCurrency>(list);
	}
}
