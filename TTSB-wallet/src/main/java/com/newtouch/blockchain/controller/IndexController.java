package com.newtouch.blockchain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("route")
public class IndexController {
	
//	@GetMapping("login")
//	public String login() {
//		return "account/login";
//	}
	
//	@GetMapping("register")
//	public String register() {
//		return "account/register";
//	}
	
	@GetMapping("index")
	public String index() {
		return "dashboard/dashboard";
	}
	@GetMapping("asset")
	public String asset() {
		return "asset/asset";
	}
	
	@GetMapping("personal")
	public String personal() {
		return "personal/detail";
	}
	
	@GetMapping("currency")
	public String currency() {
		return "currency/currency";
	}
	
	@GetMapping("transfer")
	public String transfer() {
		return "transfer/transfer";
	}
	
	@GetMapping("txrecord")
	public String txrecord() {
		return "txrecord/txrecord";
	}
	
}
