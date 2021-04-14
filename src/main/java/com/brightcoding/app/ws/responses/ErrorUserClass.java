package com.brightcoding.app.ws.responses;

import java.util.Date;

public class ErrorUserClass {
	private Date timestamp;
	private String message;
	/**
	 * @param timestamp
	 * @param message
	 */
	public ErrorUserClass(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
