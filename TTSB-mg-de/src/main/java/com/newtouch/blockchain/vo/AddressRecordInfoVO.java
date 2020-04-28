package com.newtouch.blockchain.vo;

import java.math.BigInteger;

import lombok.Data;

@Data
public class AddressRecordInfoVO {
	private String userId;
	private String address;
	private String fileName;
	private BigInteger balance;
}
