package com.funcart.domain.dto.customer;

public class UpdateCustomerDto {
	
	private String email;
	private String password;
	
	private long newPhoneNumber;
	private String newName;
	private String newBillingAddress;
	private String newShippingAddress;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getNewPhoneNumber() {
		return newPhoneNumber;
	}
	public void setNewPhoneNumber(long newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getNewBillingAddress() {
		return newBillingAddress;
	}
	public void setNewBillingAddress(String newBillingAddress) {
		this.newBillingAddress = newBillingAddress;
	}
	public String getNewShippingAddress() {
		return newShippingAddress;
	}
	public void setNewShippingAddress(String newShippingAddress) {
		this.newShippingAddress = newShippingAddress;
	}
}
