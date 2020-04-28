package com.newtouch.blockchain.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Int;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.utils.Numeric;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WalletService {
	@Autowired
	private Web3j web3j;
	
//	private static final String URL = "http://59.80.34.143:46080";
//	private static final String FILE_PATH = "F://Newtouch/ttsb/wallet/";
//	private static final String FILE_PATH = "./keystore/";
//	private static final String DEFAULT_PASSWORD = "wuyuehua123";
//	private static final String FILE_NAME = "UTC--2019-10-24T06-28-21.195979111Z--e6f990c09f779ec1510d8143938b501b19e87fbf";
//	private static final String PASSWORD = "natie1234";
//	public static final String TOKEN_ADDRESS = "0xce578E30ca9EF0090c30534BAABF45D554890E43";
	
//	private static final String QUE_QUAN_ADDRESS = "0x8Dc65B0bD1f31FA41B78cAa03a84f61BBb6a0F0b";
	private static final long GAS = 3000000L;

	
	
	/**
	 * 生成一个钱包文件到指定文件夹
	 */
	public Map<String, String> generateNewWalletFile(String filePath,String password) {
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


	public String transferToken(String contractAddress,String password,String filePath,String fileName,String from, String to, BigInteger amount) {
		log.info("transferToken start, from={},to={},amount={}", from, to, amount);
		try {
			Credentials credentials = WalletUtils.loadCredentials(password, filePath + fileName);
			if (credentials == null) {
				log.warn("transferToken,load wallet file error, credentials is null");
				return null;
			}
			String addressFrom = credentials.getAddress();
			log.info("transferToken,addressFrom={}", addressFrom);
			if (StringUtils.isBlank(addressFrom)) {
				log.warn("transferToken,load wallet file error, address is null");
				return null;
			}
			// get the next available nonce
			EthGetTransactionCount ethGetTransactionCount = web3j
					.ethGetTransactionCount(addressFrom, DefaultBlockParameterName.LATEST).send();
			if (ethGetTransactionCount == null) {
				log.warn("transferToken,get nonce, resp is null");
				return null;
			}
			if (ethGetTransactionCount.hasError()) {
				log.warn("transferToken,get nonce, resp has error:{}",
						JSONObject.toJSONString(ethGetTransactionCount.getError()));
				return null;
			}
			BigInteger nonce = ethGetTransactionCount.getTransactionCount();
			if (nonce == null) {
				log.warn("transferToken,nonce is null");
				return null;
			}
			log.info("transferToken,nonce={}", nonce.toString());
			// create our transaction
			Function function = new Function("transferOverride",
					Arrays.asList(new Address(from), new Address(to), new Uint(amount)),
					Collections.emptyList());
			String encodedFunction = FunctionEncoder.encode(function);
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, BigInteger.ZERO,
					BigInteger.valueOf(GAS), contractAddress, encodedFunction);
			// sign & send our transaction
			byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
			String hexValue = Numeric.toHexString(signedMessage);
			EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
			if (ethSendTransaction == null) {
				log.warn("transferToken,resp is null");
				return null;
			}
			if (ethSendTransaction.hasError()) {
				log.warn("transferToken,resp has error:{}", JSONObject.toJSONString(ethSendTransaction.getError()));
				return null;
			}
			String hash = ethSendTransaction.getTransactionHash();
			if (StringUtils.isNotBlank(hash)) {
				return hash;
			}
		} catch (Exception e) {
			log.error("transferToken error", e);
		}
		return null;
	}

	public BigInteger totalSupply(String contractAddress) {
		try {
			Function function = new Function("totalSupply", Collections.emptyList(),
					Collections.singletonList(new TypeReference<Uint>() {
					}));
			return (BigInteger) callContract(contractAddress, function);
		} catch (Exception e) {
			log.error("totalSupply error", e);
		}
		return null;
	}

	public BigInteger decimals(String contractAddress) {
		try {
			Function function = new Function("decimals", Collections.emptyList(),
					Collections.singletonList(new TypeReference<Uint>() {
					}));
			return (BigInteger) callContract(contractAddress, function);
		} catch (Exception e) {
			log.error("decimals error", e);
		}
		return null;
	}

	public BigInteger shareholders(String contractAddress) {
		try {
			Function function = new Function("shareholders", Collections.emptyList(),
					Collections.singletonList(new TypeReference<Uint>() {
					}));
			return (BigInteger) callContract(contractAddress, function);
		} catch (Exception e) {
			log.error("shareholders error", e);
		}
		return null;
	}

	public String holderAt(String contractAddress, long index) {
		try {
			Function function = new Function("holderAt", Collections.singletonList(new Int(BigInteger.valueOf(index))),
					Collections.singletonList(new TypeReference<Address>() {
					}));
			return (String) callContract(contractAddress, function);
		} catch (Exception e) {
			log.error("holderAt error", e);
		}
		return null;
	}

	public Boolean isHolder(String contractAddress, String address) {
		try {
			Function function = new Function("isHolder", Collections.singletonList(new Address(address)),
					Collections.singletonList(new TypeReference<Bool>() {
					}));
			Object res = callContract(contractAddress, function);
			if (res != null) {
				return (Boolean) res;
			}
		} catch (Exception e) {
			log.error("isHolder error", e);
		}
		return null;
	}

	public BigInteger balanceOf(String contractAddress, String address) {
		try {
			Function function = new Function("balanceOf", Collections.singletonList(new Address(address)),
					Collections.singletonList(new TypeReference<Uint>() {
					}));
			Object res = callContract(contractAddress, function);
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
	@SuppressWarnings("rawtypes")
	protected Object callContract(String contractAddress, Function function) {
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
	/**
	 * 16进制转10进制整数
	 * @param strHex
	 * @return
	 */
    public static BigInteger hexToBigInteger(String strHex) {
        if (strHex.length() > 2) {
            if (strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x')) {
                strHex = strHex.substring(2);
            }
            BigInteger bigInteger = new BigInteger(strHex, 16);
            return bigInteger;
        }
        return null;
    }
    
    /**
     * hex地址转地址
     * @param strHex
     * @return
     */
    public static String hexToAddress(String strHex) {
        if (strHex.length() > 42) {
            if (strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x')) {
                strHex = strHex.substring(2);
            }
            strHex = strHex.substring(24);
            return "0x" + strHex;
        }
        return null;
    }
	public static void main(String[] args) throws IOException {
		String inputData = "0x80318be8000000000000000000000000e6f990c09f779ec1510d8143938b501b19e87fbf000000000000000000000000a5c481bd2f4019d6a4b3abf5be91a7d722b82ba1000000000000000000000000000000000000000000000000000000000000000a";
		String method = inputData.substring(0, 10);
		System.out.println(method);
		String from = inputData.substring(10, 74);
        System.out.println("from:"+hexToAddress(from));
        
        String to = inputData.substring(74, 138);
        System.out.println("to:"+hexToAddress(to));
        
        System.out.println("test:"+hexToAddress("0x0000000000000000000000006830ed08d58e6fad5133594858e685cade8dd145"));
        String value = inputData.substring(138);
//        long bigInteger = Long.parseLong(value, 16);
        System.out.println("value:"+hexToBigInteger(value));
//		System.out.println(new WalletService().transferToken("0x44488d642b7f7d8d23a7ad3978691e8f841811f2", "0x6830ed08d58e6fad5133594858e685cade8dd145", new BigInteger("10922")));
	}
}
