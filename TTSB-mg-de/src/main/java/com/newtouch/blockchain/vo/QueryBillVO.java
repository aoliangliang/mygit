
package com.newtouch.blockchain.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class QueryBillVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 310789534835031735L;
	private String Id;
	private String userId;
	private String address;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int pageNum = 1;
	private int pageSize = 10;
}
