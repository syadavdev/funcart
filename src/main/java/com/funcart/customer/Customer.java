package com.funcart.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer{
	
	@Id @GeneratedValue
	private int id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column
	private long phoneNumber;
	
	//getter setter
	
	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean checkSignupDetail(){
		boolean flag = true;
		if(this.getEmail().isEmpty() || this.getPassword().isEmpty() || this.getUsername().isEmpty() || 
				Long.toString(this.getPhoneNumber()).length() != 10){
	
				flag = false;
		}
		else{
			flag = true;
		}
		return flag;
	}
}
