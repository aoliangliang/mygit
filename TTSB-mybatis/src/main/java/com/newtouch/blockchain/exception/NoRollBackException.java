/**
 * 
 */
package com.newtouch.blockchain.exception;

/**
 * 
 * default spring transaction not rollback when throw this exception
 *
 */
public class NoRollBackException extends Exception{

	/** 
	*/ 
	private static final long serialVersionUID = 9097211708116849399L;

	/**
	 * @param message
	 * @param cause
	 */
	public NoRollBackException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public NoRollBackException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NoRollBackException(Throwable cause) {
		super(cause);
	}

}
