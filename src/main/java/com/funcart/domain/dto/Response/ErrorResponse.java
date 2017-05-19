package com.funcart.domain.dto.Response;

public class ErrorResponse {

	private String errorMsg;
	private int errorCode;
	
	public ErrorResponse(){
		
	}
	
	public ErrorResponse(int errorCode, String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
