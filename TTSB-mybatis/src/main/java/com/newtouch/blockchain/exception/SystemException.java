package com.newtouch.blockchain.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统异常基类
 */
public class SystemException extends RuntimeException {

	/** 
	*/ 
	private static final long serialVersionUID = 8397527274068316449L;

	/** 错误代码 */
	@Getter
	@Setter
	private String messageCode;

	/** 错误信息 */
	@Getter
	@Setter
	private String message;

	/**
	 * @param messageKey
	 * @param message
	 */
	public SystemException(String messageCode, String message) {
		this.messageCode = messageCode;
		this.message = message;
	}

	/**
	 * @param cause
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public void print() {
		System.out.println(toString());
	}

}
