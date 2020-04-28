package com.newtouch.blockchain.controller;

import java.util.Map;

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
import com.newtouch.blockchain.entity.WalletBsTxRecord;
import com.newtouch.blockchain.entity.WalletCurrency;
import com.newtouch.blockchain.security.util.SecurityUtils;
import com.newtouch.blockchain.security.vo.CustomUserDetails;
import com.newtouch.blockchain.service.WalletBsTxRecordService;
import com.newtouch.blockchain.service.WalletCurrencyService;

import cn.kklazy.mvc.support.CommonResponse;
import cn.kklazy.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("bsTx")
@Slf4j
public class WalletBsTxRecordController {
	@Autowired
	private WalletBsTxRecordService walletBsTxRecordService;
	@Autowired
	private WalletCurrencyService walletCurrencyService;
	
	@GetMapping("page")
	@ResponseBody
	public CommonResponse getTxListByUser(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize) {
		try {
			CustomUserDetails details = SecurityUtils.getUserDetails();
			if (details == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			WalletAccount account = details.getAccount();
			if (account == null) {
				return CommonResponse.failed(StringUtils.EMPTY).setMessage("请重新登录");
			}
			PageInfo<WalletBsTxRecord> pageInfo = walletBsTxRecordService.pageByUser(account.getUsername(), pageNum, pageSize);
			return CommonResponse.success().setData(pageInfo);
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
		WalletBsTxRecord tx = walletBsTxRecordService.findById(id);
		if (tx == null) {
			model.addAttribute("message", "查询数据不存在");
			return "error/500";
		}
		String currencyId = tx.getCurrencyId();
		WalletCurrency currency = walletCurrencyService.findById(currencyId);
		if (currency == null) {
			model.addAttribute("message", "查询数据不存在");
			return "error/500";
		}
		tx.setWalletCurrency(currency);
		model.addAttribute("detail", JSON.toJSONString(tx));
		return "txrecord/detail";
	}
	
	@GetMapping("findByDate")
	@ResponseBody
	public CommonResponse findByDate() {
		try {
			Map<String, Object> map = walletBsTxRecordService.findByDay(7);
			return CommonResponse.success().setData(map);
		} catch (Exception e) {
			log.error("{}",e);
			return CommonResponse.failed(StringUtils.EMPTY).setMessage("系统错误，请联系管理员");
		}
	}
}
