package com.funcart.domain.dto.order;

import java.util.List;

import com.funcart.domain.dto.cart.CartItemDto;

public class OrderDto {
	
	private String email;
	private List<OrderItemDto> orderDtoList;
	
	public List<OrderItemDto> getOrderDtoList() {
		return orderDtoList;
	}

	public void setOrderDtoList(List<OrderItemDto> orderDtoList) {
		this.orderDtoList = orderDtoList;
	}

	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
