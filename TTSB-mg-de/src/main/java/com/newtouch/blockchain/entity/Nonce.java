package com.newtouch.blockchain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.newtouch.blockchain.mybatis.BaseEntity;

import lombok.ToString;

@ToString(callSuper = true, doNotUseGetters = true)
public class Nonce extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -1479889227839888849L;
	
	public static final String TYPE_CHONG_ZHANG = "0";
	public static final String TYPE_TRANSFER = "1";
	public static final String TYPE_ISSUE = "2";
	public static final String TYPE_ADD_QUE_QUAN = "3";
	public static final String TYPE_TRANSFER_QUE_QUAN = "4";
	public static final String STATUS_INIT = "0";
	public static final String STATUS_SENT = "1";
	public static final String STATUS_SUCCESS = "2";
	public static final String STATUS_PASS = "3";
	public static final String STATUS_EXPIRE = "4";
	public static final String SOURCE_TYZH = "TYZH";
	public static final String SOURCE_ZF = "ZF";
	public static final String SOURCE_XPQ = "XPQ";
	public static final String SOURCE_XKF = "XKF";
	public static final String SOURCE_SYS = "SYS";

	private BigInteger nonce;
	private String type;// 类型：0-冲账，1-积分转账，2-发币，3-首次上传确权信息，4-确权信息转让
	private String hash;
	private String status;// 状态：0-初始，1-已发送，2-成功，3-无需发送，4-过期
	private String fromAddress;
	private String toAddress;
	private BigInteger amount;
	private String source;// 来源TYZH/ZF/XPQ/XKF/SYS(统一账户/支付/新派遣/新开发/系统管理)
	private String owner;
	private String issuer;
	private String queQuanHash;
	private String remark;
	private LocalDateTime requestTime;// 用这个代替首次插入时间

	public BigInteger getNonce() {
		return nonce;
	}

	public void setNonce(BigInteger nonce) {
		this.nonce = nonce;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getQueQuanHash() {
		return queQuanHash;
	}

	public void setQueQuanHash(String queQuanHash) {
		this.queQuanHash = queQuanHash;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public LocalDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}

}