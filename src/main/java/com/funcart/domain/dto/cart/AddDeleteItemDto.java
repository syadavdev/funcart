package com.funcart.domain.dto.cart;

public class AddDeleteItemDto {
	
	private String email;
	private int ItemId;
	
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
