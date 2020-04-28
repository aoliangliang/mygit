package com.newtouch.blockchain.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.blockchain.dao.WalletAccountCurrencyDao;
import com.newtouch.blockchain.entity.WalletAccountCurrency;
import com.newtouch.blockchain.entity.WalletCurrency;
import com.newtouch.blockchain.exception.BusinessException;
import com.newtouch.blockchain.service.ethereum.EthereumPrivateChainService;
import com.newtouch.blockchain.service.ethereum.EthereumPublicChainService;

@Service
public class WalletAccountCurrencyService {
	@Autowired
	private WalletAccountCurrencyDao walletAccountCurrencyDao;
	@Autowired
	private WalletService walletService;
	@Autowired
	private WalletCurrencyService walletCurrencyService;
	@Autowired
	private EthereumPrivateChainService ethereumPrivateChainService;
	@Autowired
	private EthereumPublicChainService ethereumPublicChainService;
	
	public void save(WalletAccountCurrency walletAccountCurrency) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			WalletCurrency currency = walletCurrencyService.findById(walletAccountCurrency.getCurrencyId());
			if (currency == null) {
				throw new BusinessException("此币种不存在");
			}
			result = walletService.generateNewWalletFile(currency.getFilePath(),currency.getDefaultPassword());
		} catch (Exception e) {
			throw new BusinessException("创建账户失败");
		}
		String address = result.get("address");
		walletAccountCurrency.setAddress(address);
		walletAccountCurrencyDao.insert(walletAccountCurrency);
	}
	
	public List<WalletAccountCurrency> findByAccountCurrency(WalletAccountCurrency walletAccountCurrency) {
		return walletAccountCurrencyDao.findByAccountCurrency(walletAccountCurrency);
	}
	/**
	 * 查询默认账号
	 * @param walletAccountCurrency
	 * @return
	 */
	public WalletAccountCurrency findDefaultAddress(WalletAccountCurrency walletAccountCurrency) {
		List<WalletAccountCurrency> walletAccountCurrencies = findByAccountCurrency(walletAccountCurrency);
		for(WalletAccountCurrency accountCurrency : walletAccountCurrencies) {
			if (WalletAccountCurrency.DEFAULT_ADDRESS_FLAG_YES.equals(accountCurrency.getDefaultAddressFlag())) {
				return accountCurrency;
			}
		}
		return null;
	}
	/**
	 * 切换默认账户
	 * @param walletAccountCurrency
	 */
	public void updateDefaultAddress(WalletAccountCurrency walletAccountCurrency,String address) {
		List<WalletAccountCurrency> walletAccountCurrencies = findByAccountCurrency(walletAccountCurrency);
		
		for(WalletAccountCurrency temp : walletAccountCurrencies) {
			if (address.equals(temp.getAddress())) {
				temp.setDefaultAddressFlag(WalletAccountCurrency.DEFAULT_ADDRESS_FLAG_YES);
				update(temp);
			}
			if (WalletAccountCurrency.DEFAULT_ADDRESS_FLAG_YES.equals(temp.getDefaultAddressFlag())) {
				temp.setDefaultAddressFlag(WalletAccountCurrency.DEFAULT_ADDRESS_FLAG_NO);
				update(temp);
			}
		}
	}
	public WalletAccountCurrency findById(String accountCurrencyId) {
		return walletAccountCurrencyDao.selectById(accountCurrencyId);
	}
	
	public void changeDefaultAddress(WalletAccountCurrency accountCurrency) {
		WalletAccountCurrency temp = new WalletAccountCurrency();
		temp.setAccountId(accountCurrency.getAccountId());
		temp.setCurrencyId(accountCurrency.getCurrencyId());
		List<WalletAccountCurrency> accountCurrencies = walletAccountCurrencyDao.findByAccountCurrency(temp);
		for(WalletAccountCurrency accountCurrency2 : accountCurrencies) {
			if (WalletAccountCurrency.DEFAULT_ADDRESS_FLAG_YES.equals(accountCurrency2.getDefaultAddressFlag())) {
				//先取消原有默认账户
				accountCurrency2.setDefaultAddressFlag(WalletAccountCurrency.DEFAULT_ADDRESS_FLAG_NO);
				walletAccountCurrencyDao.updateById(accountCurrency2);
				break;
			}
		}
		//设置默认账户
		accountCurrency.setDefaultAddressFlag(WalletAccountCurrency.DEFAULT_ADDRESS_FLAG_YES);
		walletAccountCurrencyDao.updateById(accountCurrency);
		
	}
	
	public void update(WalletAccountCurrency walletAccountCurrency) {
		walletAccountCurrencyDao.updateById(walletAccountCurrency);
	}
	
	public BigDecimal sumBalance(String currencyId,String accountId) {
		WalletAccountCurrency accountCurrency = new WalletAccountCurrency();
		accountCurrency.setAccountId(accountId);
		accountCurrency.setCurrencyId(currencyId);
		List<WalletAccountCurrency> accountCurrencies = walletAccountCurrencyDao.findByAccountCurrency(accountCurrency);
		BigDecimal result = BigDecimal.ZERO;
		for(WalletAccountCurrency accountCurrency2 : accountCurrencies) {
			WalletCurrency currency = walletCurrencyService.findById(accountCurrency2.getCurrencyId());
			if (currency == null) {
				throw new BusinessException("次币种不存在");
			}
			BigDecimal balance = BigDecimal.ZERO;
			if (WalletCurrency.TYPE_ETHEREUM_PRIVATE_CHAIN_COIN.equals(currency.getType())) {
				balance = ethereumPrivateChainService.balance(accountCurrency2.getAddress());
			}else if (WalletCurrency.TYPE_ETHEREUM_PRIVATE_CHAIN_TOKEN.equals(currency.getType())) {
				balance = ethereumPrivateChainService.balanceToken(currency, accountCurrency2.getAddress());
			}else if (WalletCurrency.TYPE_ETHEREUM_PUBLIC_CHAIN_COIN.equals(currency.getType())) {
				balance = ethereumPublicChainService.balance(accountCurrency2.getAddress());
			}else if (WalletCurrency.TYPE_ETHEREUM_PUBLIC_CHAIN_TOKEN.equals(currency.getType())) {
				balance = ethereumPublicChainService.balanceToken(currency, accountCurrency2.getAddress());
			}
			if (balance == null) {
				throw new BusinessException("获取余额失败");
			}
			result = result.add(balance);
		}
		return result;
	}
	
}
