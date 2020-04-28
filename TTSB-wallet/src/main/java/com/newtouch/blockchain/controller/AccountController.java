package com.newtouch.blockchain.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.blockchain.entity.WalletAccount;
import com.newtouch.blockchain.entity.WalletBsTxRecord;
import com.newtouch.blockchain.entity.WalletCurrency;
import com.newtouch.blockchain.security.util.SecurityUtils;
import com.newtouch.blockchain.security.vo.CustomUserDetails;
import com.newtouch.blockchain.service.WalletBsTxRecordService;
import com.newtouch.blockchain.service.WalletCurrencyService;
import com.newtouch.blockchain.service.WalletService;
import com.newtouch.blockchain.service.ethereum.EthereumPrivateChainService;

import cn.kklazy.mvc.support.CommonResponse;
import cn.kklazy.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("account")
@Slf4j
public class AccountController {
	
	@Autowired
	private WalletService walletService;
	@Autowired
	private EthereumPrivateChainService ethereumPrivateChainService;
	@Autowired
	private WalletBsTxRecordService walletBsTxRecordService;
	@Autowired
	private WalletCurrencyService walletCurrencyService;
	/**
	 * 查询余额
	 * @return
	 */
	@GetMapping("balance")
	@ResponseBody
	public CommonResponse balanceOf() {
		try {
			CustomUserDetails details = SecurityUtils.getUserDetails();
			if (details == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccount account = details.getAccount();
			if (account == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			String address= account.getAddress();
			if (StringUtils.isBlank(address)) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("当前用户没有注册钱包");
			}
			WalletCurrency currency = new WalletCurrency();
			currency.setType(WalletCurrency.TYPE_SYSTEM_POINT);
			List<WalletCurrency> currencies = walletCurrencyService.findByEntity(currency);
			if (currencies == null || currencies.size() == 0) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("钱包积分合约地址未设置");
			}
			
			BigDecimal balance = ethereumPrivateChainService.balanceToken(currencies.get(0), address);
			if (balance == null) {
				log.error("查询用户账户地址"+address+"积分余额出错");
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("查询积分余额出错");
			}
			return CommonResponse.success().setData(balance);
		} catch (Exception e) {
			log.error("{}",e);
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
	/**
	 * 转账
	 * @param currencyId
	 * @param address
	 * @param password
	 * @param amount
	 * @return
	 */
	@PostMapping("transfer")
	@ResponseBody
	public CommonResponse transfer(@RequestParam String currencyId,@RequestParam String fromAddress,@RequestParam String toUser,@RequestParam String toAddress,@RequestParam BigInteger amount) {
		try {
			if (StringUtils.isBlank(currencyId) || StringUtils.isBlank(fromAddress) || StringUtils.isBlank(toUser) || StringUtils.isBlank(toAddress)) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("转账所需参数不能为空");
			}
			currencyId = StringUtils.trim(currencyId);
			fromAddress = StringUtils.trim(fromAddress);
			toAddress = StringUtils.trim(toAddress);
			
			if (BigInteger.valueOf(0).compareTo(amount) == 0) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("转账金额不能为0"); 
			}
			if (fromAddress.equals(toAddress)) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("转入地址和转出地址不能相同"); 
			}
			CustomUserDetails details = SecurityUtils.getUserDetails();
			if (details == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccount account = details.getAccount();
			if (account == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletCurrency currency = walletCurrencyService.findById(currencyId);
			if (currency == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("此币种存在");
			}
			BigInteger balance = walletService.balanceOf(currency.getContractAddress(), fromAddress);
			if (balance == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("获取余额失败");
			}
			if (balance.compareTo(amount) < 0) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("当前您的余额小于转账金额,请重新操作");
			}
			//todo  转账操作
			WalletBsTxRecord walletBsTxRecord =  new WalletBsTxRecord();
			walletBsTxRecord.setFromUser(details.getUsername());
			walletBsTxRecord.setFromAddress(fromAddress);
			walletBsTxRecord.setToUser(toUser);
			walletBsTxRecord.setToAddress(toAddress);
			walletBsTxRecord.setAmount(new BigDecimal(amount));
			walletBsTxRecord.setTranTime(LocalDateTime.now());
			walletBsTxRecord.setStatus(WalletBsTxRecord.STATUS_DEFAULT);
			walletBsTxRecord.setCurrencyId(currencyId);
			
			walletBsTxRecordService.saveAndTransfer(walletBsTxRecord);
			return CommonResponse.success();
		} catch (Exception e) {
			log.error("{}",e);
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
	
	@GetMapping("getBalanceByCurrencyAddress")
	@ResponseBody
	public CommonResponse getBalanceByCurrencyAddress(@RequestParam String currencyId,@RequestParam String fromAddress) {
		try {
			if (StringUtils.isBlank(currencyId) || StringUtils.isBlank(fromAddress)) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("参数不能为空");
			}
			//currency todo
			WalletCurrency currency = walletCurrencyService.findById(currencyId);
			if (currency == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("此币种存在");
			}
			BigInteger balance = walletService.balanceOf(currency.getContractAddress(), fromAddress);
			if (balance == null) {
				balance = new BigInteger("0");
			}
			return CommonResponse.success().setData(balance);
		} catch (Exception e) {
			log.error("{}",e);
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
	
	
}
