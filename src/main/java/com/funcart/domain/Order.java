package com.funcart.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String customerName;
	@Column
	private int itemId;

	@Column
	private String itemName;
	
	@Column
	private String paymentMode;
	
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	@Column
	private int customerId;
	
	@Column
	private long customerPhoneNumber;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getBillingAddressId() {
		return billingAddressId;
	}
	public void setBillingAddressId(String billingAddressId) {
		this.billingAddressId = billingAddressId;
	}
	public String getShippingAddressId() {
		return shippingAddressId;
	}
	public void setShippingAddressId(String shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
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
	
	public long getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(long customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
