package com.newtouch.blockchain.exception;

/**
 * 数据访问异常
 * 
 */
public class DaoException extends SystemException {

	/** 
	*/ 
	private static final long serialVersionUID = -4710887798441906887L;

	/**
	 * @param messageKey
	 * @param message
	 */
	public DaoException(String messageKey, String message) {
		super(messageKey, message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

}
