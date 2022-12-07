package com.cognizant.auth.exception;

public class NotFoundUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundUserException(String msg){
		super(msg);
	}
}
