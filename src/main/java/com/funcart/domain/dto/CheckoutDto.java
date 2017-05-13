package com.funcart.domain.dto;

import java.util.List;
import com.funcart.domain.dto.cart.CartItemDto;

public class CheckoutDto {

	private String email;

	private List<CartItemDto> itemDtoList;
	private List<CheckaddressDto> addressList;

	public List<CheckaddressDto> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<CheckaddressDto> addressList) {
		this.addressList = addressList;
	}

	public List<CartItemDto> getItemDtoList() {
		return itemDtoList;
	}

	public void setItemDtoList(List<CartItemDto> itemDtoList) {
		this.itemDtoList = itemDtoList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
