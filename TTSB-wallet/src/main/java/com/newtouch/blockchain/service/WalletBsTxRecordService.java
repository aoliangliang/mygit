package com.newtouch.blockchain.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newtouch.blockchain.dao.WalletBsTxRecordDao;
import com.newtouch.blockchain.entity.WalletBsTxRecord;
import com.newtouch.blockchain.entity.WalletCurrency;
import com.newtouch.blockchain.exception.BusinessException;
import com.newtouch.blockchain.util.DateUtils;

import cn.kklazy.utils.StringUtils;

@Service
public class WalletBsTxRecordService {
	@Autowired
	private WalletBsTxRecordDao walletBsTxRecordDao;
	@Autowired
	private WalletService walletService;
	@Autowired
	private WalletCurrencyService walletCurrencyService;
	
	public String saveAndTransfer(WalletBsTxRecord record) {
		String hash = StringUtils.EMPTY;
		try {
			WalletCurrency currency = walletCurrencyService.findById(record.getCurrencyId());
			if (currency == null) {
				throw new BusinessException("此币种不存在");
			}
			hash = walletService.transferToken(currency.getContractAddress(),currency.getPassword(),currency.getFilePath(),currency.getFileName(),record.getFromAddress(),record.getToAddress(),new BigInteger(record.getAmount().toString()));
		} catch (Exception e) {
			throw new BusinessException("链上转账失败");
		}
		if (StringUtils.isBlank(hash)) {
			throw new BusinessException("链上转账失败");
		}
		record.setHash(hash);
		walletBsTxRecordDao.insert(record);
		return hash;
	}
	
	public List<WalletBsTxRecord> findByBsTx(WalletBsTxRecord record) {
		return walletBsTxRecordDao.findByBsTx(record);
	}
	
	public List<WalletBsTxRecord> findByUser(String username) {
		return walletBsTxRecordDao.findByUsername(username);
	}
	
	public PageInfo<WalletBsTxRecord> pageByUser(String username,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<WalletBsTxRecord> walletBsTxRecords = findByUser(username);
		return new PageInfo<WalletBsTxRecord>(walletBsTxRecords);
	}
	
	public WalletBsTxRecord findById(String id) {
		return walletBsTxRecordDao.selectById(id);
	}
	public List<WalletBsTxRecord> findByDate(LocalDateTime startDateTime,LocalDateTime enDateTime){
		return walletBsTxRecordDao.findByDate(DateUtils.format(startDateTime), DateUtils.format(enDateTime));
	}
	
	public Map<String, Object> findByDay(int day) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		for (int i = day; i >= 0; i--) {
			LocalDate today = LocalDate.now();
			LocalDateTime min = LocalDateTime.of(today.plusDays(-i), LocalTime.MIN);
			LocalDateTime max = LocalDateTime.of(today.plusDays(-i), LocalTime.MAX);
			List<WalletBsTxRecord> txRecords = findByDate(min, max);
			result.put(DateUtils.format(today.plusDays(-i)), txRecords.size());
		}
		return result;
	}
}
