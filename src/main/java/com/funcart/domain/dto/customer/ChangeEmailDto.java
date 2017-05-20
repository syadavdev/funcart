package com.funcart.domain.dto.customer;

public class ChangeEmailDto {

	private String oldEmail;
	private String NewEmail;
	private String password;
	
	public String getOldEmail() {
		return oldEmail;
	}
	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}
	public String getNewEmail() {
		return NewEmail;
	}
	public void setNewEmail(String newEmail) {
		NewEmail = newEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
