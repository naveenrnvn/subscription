package com.subscription.Exceptions;

public class AddSubscriptionFailed extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	String reason;

	public AddSubscriptionFailed(String message, String reason) {
		super();
		this.message = message;
		this.reason = reason;
	}
	
	public AddSubscriptionFailed() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
	
	
	
	

}
