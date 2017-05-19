package com.funcart.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Order")
public class Order {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column
	private String customerName;
	
	@Column
	private int itemId;

	@Column
	private int customerId;
	
	@Column
	private long customerPhoneNumber;
	@Column
	private String billingAddressId;
	@Column
	private String shippingAddressId;
	
	
	@Column
	private Double price;
	
	
	@Column
	private int quantity;

	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getcustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setcustomerPhoneNumber(long customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	

}
