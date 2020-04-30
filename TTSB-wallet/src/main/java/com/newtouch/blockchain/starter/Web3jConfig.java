package com.newtouch.blockchain.starter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {
//	@Value("${blockchain.url}")
//	private String url;
//	
//	@Bean
//	public Web3j web3j() {
//		return Web3j.build(buildService());
//	}
//	@Bean
//	public Admin admin() {
//		return Admin.build(buildService());
//	}
//	
//	private Web3jService buildService() {
//		return new HttpService(url);
//	}
}
