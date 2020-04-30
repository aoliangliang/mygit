package com.newtouch.blockchain.service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContractService {
	
//	@Value("${blockchain.url}")
//	private String url;
	
	@Value("${blockchain.token-address}")
	private String tokenAddress;
	
//	@Autowired
	private Web3j web3j;

//	private Admin buildAdmin() {
//		return Admin.build(new HttpService(url));
//	}

//	private Web3j buildWeb3j() {
//		return Web3j.build(new HttpService(url));
//	}
	

	public BigInteger balanceOf(String address) {
		try {
			Function function = new Function("balanceOf", Collections.singletonList(new Address(address)),
					Collections.singletonList(new TypeReference<Uint>() {
					}));
			Object res = callContract(tokenAddress, function);
			if (res != null) {
				return (BigInteger) res;
			}
		} catch (Exception e) {
			log.error("balanceOf error", e);
		}
		return null;
	}

	/**
	 * 网络测试
	 */
	public String version() {
		try {
//			Web3j web3 = buildWeb3j();
			Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
			if (web3ClientVersion == null) {
				log.warn("version-resp is null");
				return null;
			}
			if (web3ClientVersion.hasError()) {
				log.warn("version-resp has error:{}", JSONObject.toJSONString(web3ClientVersion.getError()));
				return null;
			}
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			if (StringUtils.isNotBlank(clientVersion)) {
				return clientVersion;
			}
		} catch (Exception e) {
			log.error("version-get version error", e);
		}
		return null;
	}

	/**
	 * 调用合约的静态方法(1个返回值)
	 * 
	 * @param contractAddress
	 * @param function
	 * @return
	 */
	protected Object callContract(String contractAddress, Function function) {
		try {
			String encodedFunction = FunctionEncoder.encode(function);
//			Web3j web3 = buildWeb3j();
			EthCall response = web3j
					.ethCall(Transaction.createEthCallTransaction(null, contractAddress, encodedFunction),
							DefaultBlockParameterName.LATEST)
					.send();
			if (response == null) {
				log.warn("callContract,resp is null");
				return null;
			}
			if (response.hasError()) {
				log.warn("callContract,resp has error:{}", JSONObject.toJSONString(response.getError()));
				return null;
			}
			@SuppressWarnings("rawtypes")
			List<Type> someTypes = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
			return someTypes.get(0).getValue();
		} catch (Exception e) {
			log.error("callContract error", e);
		}
		return null;
	}
}
