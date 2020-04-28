package com.newtouch.blockchain.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.Block;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WalletEthService {
	
	@Autowired
	private Web3j web3j;
	/**
	 * 获取区块数
	 * @return
	 */
	public BigInteger getEthBlockNumber() {
		BigInteger blockNumber = null;
		try {
			EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().send();
			if (ethBlockNumber == null) {
				log.error("ethBlockNumber is null");
				return blockNumber;
			}
			if (ethBlockNumber.hasError()) {
				log.error("ethBlockNumber has error:{}",JSONObject.toJSONString(ethBlockNumber.getError()));
				return blockNumber;
			}
			blockNumber = ethBlockNumber.getBlockNumber();
			return blockNumber;
		} catch (IOException e) {
			log.error("error:"+e.getMessage());
			return blockNumber;
		}
	}
	
	public Block getEthBlock(BigInteger blockNumber) {
		try {
			EthBlock ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber),true).send();
			if (ethBlock == null) {
				return null;
			}
			if (ethBlock.hasError()) {
				return null;
			}
			return ethBlock.getBlock();
		} catch (IOException e) {
			log.error("{}",e);
		}
		return null;
	}
	/**
	 * 获取交易收据
	 * @param hash
	 * @return
	 */
	public TransactionReceipt geTransactionReceipt(String hash) {
		try {
			EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(hash).send();
			if (transactionReceipt == null) {
				return null;
			}
			if (transactionReceipt.hasError()) {
				return null;
			}
			Optional<TransactionReceipt> optional = transactionReceipt.getTransactionReceipt();
			if (optional == null) {
				return null;
			}
			return optional.get();
		} catch (Exception e) {
			log.error("{}", e);
		}
		return null;
	}
	public Transaction getTransaction(String hash) {
		EthTransaction ethTransaction = null;
		try {
			ethTransaction = web3j.ethGetTransactionByHash(hash).send();
			if (ethTransaction == null) {
				return null;
			}
			if (ethTransaction.hasError()) {
				return null;
			}
			return ethTransaction.getResult();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("{}",e);
		}
		return null;
	}
	
	/**
	 * 16进制转10进制整数
	 * @param strHex
	 * @return
	 */
    public BigInteger hexToBigInteger(String strHex) {
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
    public String hexToAddress(String strHex) {
        if (strHex.length() > 42) {
            if (strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x')) {
                strHex = strHex.substring(2);
            }
            strHex = strHex.substring(24);
            return "0x" + strHex;
        }
        return null;
    }
}
