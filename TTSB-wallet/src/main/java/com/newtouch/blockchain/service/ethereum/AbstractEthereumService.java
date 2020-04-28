package com.newtouch.blockchain.service.ethereum;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import com.alibaba.fastjson.JSONObject;
import com.newtouch.blockchain.entity.WalletCurrency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractEthereumService {
	protected static final String DEFAULT_DIGITS = "1000000000000000000";
	
	protected abstract Web3j initWeb3j();
	
	public BigDecimal balance(String address) {
		return getEthBalance(initWeb3j(), address);
	}
	public BigDecimal balanceToken(WalletCurrency currency,String address) {
		return getTokenBalance(initWeb3j(), currency, address);
	}
	public Map<String, String> createAccount(WalletCurrency currency,String password){
		return generateEthNewWalletFile(currency.getFilePath(), password);
	}
	
	private Map<String, String> generateEthNewWalletFile(String filePath,String password) {
		log.info("generateNewWalletFile start");
		try {
			String fileName = WalletUtils.generateLightNewWalletFile(password, new File(filePath));
			if (StringUtils.isNotBlank(fileName)) {
				Credentials credentials = WalletUtils.loadCredentials(password, filePath + fileName);
				if (credentials == null) {
					log.warn("generateNewWalletFile,load wallet file error, credentials is null");
					return null;
				}
				String address = credentials.getAddress();
				log.info("generateNewWalletFile,address={}", address);
				if (StringUtils.isBlank(address)) {
					log.warn("generateNewWalletFile,load wallet file error, address is null");
					return null;
				}
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("fileName", fileName);
				ret.put("address", address);
				return ret;
			}
		} catch (Exception e) {
			log.error("generateNewWalletFile error", e);
		}
		return null;
	}
	
	private BigDecimal getEthBalance(Web3j web3j,String address) {
		BigInteger balance = BigInteger.ZERO;
		try {
			EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
			if (ethGetBalance == null) {
				log.error("获取账户"+address+"余额出错");
				return null;
			}
			if (ethGetBalance.hasError()) {
				log.error("获取账户"+address+"余额出错:"+JSONObject.toJSONString(ethGetBalance.getError()));
				return null;
			}
			balance = ethGetBalance.getBalance();
			BigDecimal bigDecimal = new BigDecimal(balance);
			return bigDecimal.divide(new BigDecimal(DEFAULT_DIGITS));
		} catch (Exception e) {
			log.error("获取账户"+address+"余额出错:"+e.getMessage());
			return null;
		}
	}
	
	private BigDecimal getTokenBalance(Web3j web3j, WalletCurrency currency, String address) {
		// TODO Auto-generated method stub
		try {
			Function function = new Function("balanceOf", Collections.singletonList(new Address(address)),
					Collections.singletonList(new TypeReference<Uint>() {
					}));
			Object res = callContract(web3j, currency.getContractAddress(), function);
			BigInteger balance = BigInteger.ZERO;
			if (res != null) {
				balance = (BigInteger) res;
			}
			BigDecimal bigDecimal = new BigDecimal(balance);
			return bigDecimal;
		} catch (Exception e) {
			log.error("balanceOf error", e);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	private Object callContract(Web3j web3j, String contractAddress, Function function) {
		try {
			String encodedFunction = FunctionEncoder.encode(function);
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
			List<Type> someTypes = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
			return someTypes.get(0).getValue();
		} catch (Exception e) {
			log.error("callContract error", e);
		}
		return null;
	}
}
