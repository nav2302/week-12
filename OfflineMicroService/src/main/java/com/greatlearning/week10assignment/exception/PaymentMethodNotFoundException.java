package com.greatlearning.week10assignment.exception;

public class PaymentMethodNotFoundException extends RuntimeException{
	/**
	 * @author nav
	 */

	private static final long serialVersionUID = 1L;
	private String message;

	public PaymentMethodNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}