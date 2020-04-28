package com.newtouch.blockchain.entity;

import java.io.Serializable;

import com.newtouch.blockchain.mybatis.BaseEntity;

import lombok.ToString;

@ToString(callSuper = true, doNotUseGetters = true)
public class AddressRecord extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4112281529998607360L;

	private String userId;
	private String address;
	private String fileName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}