package com.newtouch.blockchain.service.ethereum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;

import com.newtouch.blockchain.starter.EthereumConnectionInfo;

@Service
public class EthereumPublicChainService extends AbstractEthereumService{
	
	@Autowired
	private EthereumConnectionInfo ethereumConnectionInfo;

	@Override
	protected Web3j initWeb3j() {
		// TODO Auto-generated method stub
		return ethereumConnectionInfo.getPublicChainWeb3j();
	}

}
