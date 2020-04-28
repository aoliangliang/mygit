package com.newtouch.blockchain.vo;

import java.util.List;

import lombok.Data;
@Data
public class BlockVO {
	private String author;
    private long difficulty;
    private String difficultyRaw;
    private String extraData;
    private long gasLimit;
    private String gasLimitRaw;
    private long gasUsed;
    private String gasUsedRaw;
    private String hash;
    private String logsBloom;
    private String miner;
    private String mixHash;
    private long nonce;
    private String nonceRaw;
    private long number;
    private String numberRaw;
    private String parentHash;
    private String receiptsRoot;
    private String sealFields;
    private String sha3Uncles;
    private long size;
    private String sizeRaw;
    private String stateRoot;
    private long timestamp;
    private String timestampRaw;
    private long totalDifficulty;
    private String totalDifficultyRaw;
    private List<TransactionVO> transactions;
    private String transactionsRoot;
    private List<String> uncles;
}
