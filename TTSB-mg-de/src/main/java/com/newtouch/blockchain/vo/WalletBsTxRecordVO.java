package com.newtouch.blockchain.vo;

import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.Data;

@Data
public class WalletBsTxRecordVO implements Serializable{
	
	private static final long serialVersionUID = -2587104174690829002L;
	
	private String id;
	private String username;
	private String address;
	private String hash;
	private String status;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String currencyId;
	// 币种
	private String currencyName;
	private String currencySymbol;
}
