package com.funcart.domain.dto.error;

public class SuccessResponse {
	private String successMsg;
	private int successCode;
	
	public SuccessResponse(String successMsg, int successCode) {
		super();
		this.successMsg = successMsg;
		this.successCode = successCode;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public int getSuccessCode() {
		return successCode;
	}
	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}
	
}
