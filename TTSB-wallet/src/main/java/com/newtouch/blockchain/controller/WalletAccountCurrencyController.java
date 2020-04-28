package com.newtouch.blockchain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.blockchain.entity.WalletAccount;
import com.newtouch.blockchain.entity.WalletAccountCurrency;
import com.newtouch.blockchain.entity.WalletCurrency;
import com.newtouch.blockchain.security.util.SecurityUtils;
import com.newtouch.blockchain.security.vo.CustomUserDetails;
import com.newtouch.blockchain.service.WalletAccountCurrencyService;
import com.newtouch.blockchain.service.WalletAccountService;
import com.newtouch.blockchain.service.WalletCurrencyService;

import cn.kklazy.mvc.support.CommonResponse;
import cn.kklazy.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("walletAccountCurrency")
@Slf4j
public class WalletAccountCurrencyController {
	@Autowired
	private WalletAccountCurrencyService walletAccountCurrencyService;
	@Autowired
	private WalletCurrencyService walletCurrencyService;
	@Autowired
	private WalletAccountService walletAccountService;
	/**
	 * 添加币种，并创建账号
	 * @param currencyId
	 * @return
	 */
	@PostMapping("add")
	@ResponseBody
	public CommonResponse addCurrency(@RequestParam String currencyId) {
		try {
			if (StringUtils.isBlank(currencyId)) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("参数不能为空");
			}
			WalletCurrency currency = walletCurrencyService.findById(currencyId);
			if (currency == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("当前币种不存在，请重新操作");
			}
			if (WalletCurrency.STATUS_NO.equals(currency.getStatus())) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("当前币种已禁用，不可添加");
			}
			CustomUserDetails details = SecurityUtils.getUserDetails();
			if (details == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccount account = details.getAccount();
			if (account == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccountCurrency walletAccountCurrency = new WalletAccountCurrency();
			walletAccountCurrency.setAccountId(account.getId());
			walletAccountCurrency.setCurrencyId(currencyId);
			List<WalletAccountCurrency> result = walletAccountCurrencyService.findByAccountCurrency(walletAccountCurrency);
			if (result == null || result.isEmpty()) {
				walletAccountCurrency.setDefaultAddressFlag(WalletAccountCurrency.DEFAULT_ADDRESS_FLAG_YES);
			}
			if (result != null && result.size() >= 1) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("单个币种只能创建一个账号");
			}
			walletAccountCurrencyService.save(walletAccountCurrency);
			return CommonResponse.success();
		} catch (Exception e) {
			log.error("添加币种出错:{}"+e.getMessage());
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
	/**
	 * 
	 * @param currencyId
	 * @return
	 */
	@GetMapping("getAddress")
	@ResponseBody
	public CommonResponse getAddress(@RequestParam String currencyId) {
		try {
			if (StringUtils.isBlank(currencyId)) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("参数不能为空");
			}
			if (walletCurrencyService.findById(currencyId) == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("当前币种不存在，请重新操作");
			}
			CustomUserDetails details = SecurityUtils.getUserDetails();
			if (details == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccount account = details.getAccount();
			if (account == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccountCurrency walletAccountCurrency = new WalletAccountCurrency();
			walletAccountCurrency.setAccountId(account.getId());
			walletAccountCurrency.setCurrencyId(currencyId);
			List<WalletAccountCurrency> result = walletAccountCurrencyService.findByAccountCurrency(walletAccountCurrency);
			if (result != null && !result.isEmpty()) {
				return CommonResponse.success().setData(result);
			}else {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("查询数据不存在");
			}
			
		} catch (Exception e) {
			log.error("获取用户和币种对应的账户地址出错:"+e.getMessage());
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
		
	}
	/**
	 * 获取用户名和币种对应的默认账户
	 * @param currencyId
	 * @param username
	 * @return
	 */
	@GetMapping("getAddressByUsername")
	@ResponseBody
	public CommonResponse getAddressByUsername(@RequestParam String currencyId,@RequestParam String username) {
		try {
			if (StringUtils.isBlank(currencyId) || StringUtils.isBlank(username)) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("参数不能为空");
			}
			currencyId = StringUtils.trim(currencyId);
			username = StringUtils.trim(username);
			WalletAccount account = walletAccountService.findByUsername(username);
			if (account == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("此用户不存在");
			}
			WalletAccountCurrency param = new WalletAccountCurrency();
			param.setAccountId(account.getId());
			param.setCurrencyId(currencyId);
			
			WalletAccountCurrency accountCurrency = walletAccountCurrencyService.findDefaultAddress(param);
			if (accountCurrency == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("查询数据不存在");
			}
			return CommonResponse.success().setData(accountCurrency.getAddress());
		} catch (Exception e) {
			log.error("获取用户名和币种对应的默认账户出错:"+e.getMessage());
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
	/**
	 * 切换默认账户地址
	 * @param currencyId
	 * @param address
	 * @return
	 */
	@PostMapping("updateDefaultAddress")
	@ResponseBody
	public CommonResponse updateDefaultAddress(@RequestParam String accountCurrencyId) {
		try {
			if (StringUtils.isBlank(accountCurrencyId)) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
			}
			CustomUserDetails details = SecurityUtils.getUserDetails();
			if (details == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccount account = details.getAccount();
			if (account == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccountCurrency temp = walletAccountCurrencyService.findById(accountCurrencyId);
			if (temp == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("查询数据不存在");
			}
			if (WalletAccountCurrency.DEFAULT_ADDRESS_FLAG_YES.equals(temp.getDefaultAddressFlag())) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("已是默认账户地址,不可重复设置");
			}
			walletAccountCurrencyService.changeDefaultAddress(temp);
			return CommonResponse.success().setData(temp.getCurrencyId());
		} catch (Exception e) {
			log.error("切换默认账户出错:"+e.getMessage());
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
}
