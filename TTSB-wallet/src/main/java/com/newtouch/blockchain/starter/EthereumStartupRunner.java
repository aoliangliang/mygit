package com.newtouch.blockchain.starter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;

import com.newtouch.blockchain.base.BaseWeb3;
import com.newtouch.blockchain.entity.WalletNode;
import com.newtouch.blockchain.service.WalletNodeService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EthereumStartupRunner extends BaseWeb3 implements CommandLineRunner{
	@Autowired
	private WalletNodeService walletNodeService;
	@Autowired
	private EthereumConnectionInfo ethereumConnectionInfo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		log.info("实例化web3j start");
		try {
			List<WalletNode> nodes = walletNodeService.findAll();
			for(WalletNode node : nodes) {
				String url = node.getUrl();
				Web3j web3j = Web3j.build(buildService(url));
				Admin admin = Admin.build(buildService(url));
				if (WalletNode.TYPE_ETH_PUBLIC_CHAIN.equals(node.getType())) {
					ethereumConnectionInfo.setPublicChainWeb3j(web3j);
					ethereumConnectionInfo.setPublicChainAdmin(admin);
				}else if (WalletNode.TYPE_ETH_PRIVATE_CHAIN.equals(node.getType())) {
					ethereumConnectionInfo.setPrivateChainWeb3j(web3j);
					ethereumConnectionInfo.setPrivateChainAdmin(admin);
				}
			}
		} catch (Exception e) {
			log.error("实例化web3j出错");
			throw new RuntimeException("实例化web3j出错");
		}
		log.info("实例化web3j end");
	}
}
