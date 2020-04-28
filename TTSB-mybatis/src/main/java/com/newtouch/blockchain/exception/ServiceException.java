package com.newtouch.blockchain.exception;

/**
 * 
 */
public class ServiceException extends SystemException {


	/** 
	*/ 
	private static final long serialVersionUID = 3308053802428802897L;

	/**
	 * @param messageKey
	 * @param message
	 */
	public ServiceException(String messageKey, String message) {
		super(messageKey, message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
