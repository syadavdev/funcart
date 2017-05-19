package com.funcart.domain.dto.cart;

import java.util.List;

public class UpdateCartDto {
	private String email;
	private String password;
	private List<UpdateCartItemDto> updateCartItem;
	
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
	public List<UpdateCartItemDto> getUpdateCartItem() {
		return updateCartItem;
	}
	public void setUpdateCartItem(List<UpdateCartItemDto> updateCartItem) {
		this.updateCartItem = updateCartItem;
	}
}
