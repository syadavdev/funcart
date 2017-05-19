package com.funcart.domain.dto;

public class LoginDto {
	
	private String emailOrPhoneNumber;
	private String password;

	public String getEmailOrPhoneNumber() {
		return emailOrPhoneNumber;
	}

	public void setEmailOrPhoneNumber(String emailOrPhoneNumber) {
		this.emailOrPhoneNumber = emailOrPhoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
