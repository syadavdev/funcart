package com.funcart.domain.dto;

public class CheckoutDto {
	
	private String email;
	private String customerName;
	private String CustmomerPhoneNumber;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustmomerPhoneNumber() {
		return CustmomerPhoneNumber;
	}

	public void setCustmomerPhoneNumber(String custmomerPhoneNumber) {
		CustmomerPhoneNumber = custmomerPhoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
