package com.newtouch.blockchain.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.newtouch.blockchain.entity.WalletAccount;
import com.newtouch.blockchain.entity.WalletAccountCurrency;
import com.newtouch.blockchain.entity.WalletCurrency;
import com.newtouch.blockchain.security.util.SecurityUtils;
import com.newtouch.blockchain.security.vo.CustomUserDetails;
import com.newtouch.blockchain.service.WalletAccountCurrencyService;
import com.newtouch.blockchain.service.WalletCurrencyService;

import cn.kklazy.mvc.support.CommonResponse;
import cn.kklazy.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("currency")
@Slf4j
public class WalletCurrencyController {
	@Autowired
	private WalletCurrencyService walletCurrencyService;
	@Autowired
	private WalletAccountCurrencyService walletAccountCurrencyService;
	
	@GetMapping("page")
	@ResponseBody
	public CommonResponse page(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize) {
		try {
			PageInfo<WalletCurrency> page = walletCurrencyService.pageCurrencyExcludePoint(pageNum, pageSize);
			return CommonResponse.success().setData(page);
		} catch (Exception e) {
			log.error("{}",e);
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
	
//	@GetMapping("detail")
//	@ResponseBody
//	public CommonResponse detail(@RequestParam String id) {
//		try {
//			if (StringUtils.isBlank(id)) {
//				return CommonResponse.failed(StringUtils.EMPTY).setMessage("id不能为空");
//			}
//			WalletCurrency currency = walletCurrencyService.findById(id);
//			return CommonResponse.success().setData(currency);
//		} catch (Exception e) {
//			log.error("{}",e);
//			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
//		}
//	}
	@GetMapping("list")
	@ResponseBody
	public CommonResponse getCurrencyList() {
		try {
			List<WalletCurrency> walletCurrencies = walletCurrencyService.findByStatus(WalletCurrency.STATUS_YES);
			return CommonResponse.success().setData(walletCurrencies);
		} catch (Exception e) {
			log.error("{}",e);
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
	/**
	 * 详情
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("detail")
	public String detail(Model model,@RequestParam String id) {
		if (StringUtils.isBlank(id)) {
			model.addAttribute("message", "参数不能为空");
			return "error/500";
		}
		WalletCurrency currency = walletCurrencyService.findById(id);
		if (currency == null) {
			model.addAttribute("message", "查询数据不存在");
			return "error/500";
		}
		model.addAttribute("detail", JSON.toJSONString(currency));
		return "currency/detail";
	}
	
	@GetMapping("assetDetail")
	public String myInfoDetail(Model model,@RequestParam String id) {
		if (StringUtils.isBlank(id)) {
			model.addAttribute("message", "参数不能为空");
			return "error/500";
		}
		WalletCurrency currency = walletCurrencyService.findById(id);
		if (currency == null) {
			model.addAttribute("message", "查询数据不存在");
			return "error/500";
		}
		CustomUserDetails details = SecurityUtils.getUserDetails();
		if (details == null) {
			model.addAttribute("message", "请重新登录");
			return "error/500";
		}
		WalletAccount account = details.getAccount();
		if (account == null) {
			model.addAttribute("message", "请重新登录");
			return "error/500";
		}
		WalletAccountCurrency accountCurrency = new WalletAccountCurrency();
		accountCurrency.setAccountId(account.getId());
		accountCurrency.setCurrencyId(id);
		List<WalletAccountCurrency> accountCurrencies = walletAccountCurrencyService.findByAccountCurrency(accountCurrency);
		BigDecimal totalBalance = walletAccountCurrencyService.sumBalance(id, account.getId());
		
		model.addAttribute("detail", currency);
		model.addAttribute("addressList", accountCurrencies);
		model.addAttribute("totalBalance", totalBalance);
		return "asset/detail";
	}
	/**
	 * 查询当前账户下拥有的币种
	 * @return
	 */
	@GetMapping("findByAccount")
	@ResponseBody
	public CommonResponse findByAccount(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize) {
		try {
			CustomUserDetails details = SecurityUtils.getUserDetails();
			if (details == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccount account = details.getAccount();
			if (account == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
//			List<WalletCurrency> walletCurrencies = walletCurrencyService.findByAccount(account.getId());
			PageInfo<WalletCurrency> pageInfo = walletCurrencyService.pageByAccount(account.getId(), pageNum, pageSize);
			return CommonResponse.success().setData(pageInfo);
		} catch (Exception e) {
			log.error("{}",e);
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
	/**
	 * 校验当前币种是否可发起转账
	 * @param id
	 * @return
	 */
	@GetMapping("beforeTransfer")
	@ResponseBody
	public CommonResponse toTransfer(@RequestParam String id) {
		if (StringUtils.isBlank(id)) {
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("参数不能为空");
		}
		WalletCurrency currency = walletCurrencyService.findById(id);
		if (currency == null) {
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("当前币种不存在，请重新操作");
		}
		if (WalletCurrency.STATUS_NO.equals(currency.getStatus())) {
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("当前币种已禁用，不可发起转账");
		}
		return CommonResponse.success().setData(id);
	}
	
	@GetMapping("toTransfer")
	public String toTransfer(Model model,@RequestParam String id) {
		if (StringUtils.isBlank(id)) {
			model.addAttribute("message", "参数不能为空");
			return "error/500";
		}
		WalletCurrency currency = walletCurrencyService.findById(id);
		if (currency == null) {
			model.addAttribute("message", "查询数据不存在");
			return "error/500";
		}
		CustomUserDetails details = SecurityUtils.getUserDetails();
		if (details == null) {
			model.addAttribute("message", "请重新登录");
			return "error/500";
		}
		WalletAccount account = details.getAccount();
		if (account == null) {
			model.addAttribute("message", "请重新登录");
			return "error/500";
		}
		WalletAccountCurrency walletAccountCurrency = new WalletAccountCurrency();
		walletAccountCurrency.setAccountId(account.getId());
		walletAccountCurrency.setCurrencyId(id);
		List<WalletAccountCurrency> result = walletAccountCurrencyService.findByAccountCurrency(walletAccountCurrency);
		model.addAttribute("currencyId", id);
		model.addAttribute("addressList", result);
		return "transfer/transfer";
	}
}
