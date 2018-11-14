package com.nagarro.java.training.saleo.errors;

public class ErrorResponse {

	private int statusCode;
	
	private String errorMessage;
	
	private long timeStamp;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ErrorResponse() {}
	
	public ErrorResponse(int statusCode, String errorMessage, long timeStamp) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "ErrorResponse [statusCode=" + statusCode + ", errorMessage=" + errorMessage + ", timeStamp=" + timeStamp
				+ "]";
	}
	
}
