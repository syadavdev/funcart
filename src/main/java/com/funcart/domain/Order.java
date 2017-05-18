package com.funcart.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checkout")
public class Checkout {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	
	@Column
	private String customerName;
	
	@Column
	private int customerPhoneNumber;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getcustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setcustomerPhoneNumber(int customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	

}
