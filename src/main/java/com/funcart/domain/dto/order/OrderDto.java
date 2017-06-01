package com.funcart.domain.dto.order;

import java.util.List;



public class OrderDto {
	
	private String email;
	private List<OrderItemDto> orderDtoList;
	private List<OrderCustomerDto> ordercustomerDtoList;
	
	public List<OrderCustomerDto> getOrdercustomerDtoList() {
		return ordercustomerDtoList;
	}

	public void setOrdercustomerDtoList(List<OrderCustomerDto> ordercustomerDtoList) {
		this.ordercustomerDtoList = ordercustomerDtoList;
	}

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
