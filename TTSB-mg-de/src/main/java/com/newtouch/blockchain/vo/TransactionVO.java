package com.newtouch.blockchain.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionVO {
	private String blockHash;
	private long blockNumber;
	private String blockNumberRaw;
	private long chainId;
	private String creates;
	private String from;
	private BigDecimal gas;
	private BigDecimal gasPrice;
	private String gasPriceRaw;
	private String gasRaw;
	private String hash;
	private String input;
	private long nonce;
	private String nonceRaw;
	private String publicKey;
	private String r;
	private String raw;
	private String s;
	private String to;
	private long transactionIndex;
	private String transactionIndexRaw;
	private BigDecimal v;
	private BigDecimal value;
	private String valueRaw;

	@Data
	public class Transaction {
		private String blockHash;
		private long blockNumber;
		private String blockNumberRaw;
		private String contractAddress;
		private BigDecimal cumulativeGasUsed;
		private String cumulativeGasUsedRaw;
		private String from;
		private BigDecimal gasUsed;
		private String gasUsedRaw;
//		private List<String> logs;
		private String logsBloom;
		private String root;
		private String status;
		private boolean statusOK;
		private String to;
		private String transactionHash;
		private long transactionIndex;
		private String transactionIndexRaw;
	}
}
