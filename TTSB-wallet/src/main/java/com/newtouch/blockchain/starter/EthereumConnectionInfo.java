package com.newtouch.blockchain.starter;

import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;

import lombok.Data;

@Component
@Data
public class EthereumConnectionInfo {
	private Web3j privateChainWeb3j;
	private Web3j publicChainWeb3j;
	private Admin privateChainAdmin;
	private Admin publicChainAdmin;
}
