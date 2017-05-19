package com.funcart.domain.dto;

import java.util.List;

public class AddItemListDto {
	
	private String sellerName;
	private String sellerPasscode;
	private List<ItemDto> itemDtoList;
	
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerPasscode() {
		return sellerPasscode;
	}
	public void setSellerPasscode(String sellerPasscode) {
		this.sellerPasscode = sellerPasscode;
	}
	public List<ItemDto> getItemDtoList() {
		return itemDtoList;
	}
	public void setItemDtoList(List<ItemDto> itemDtoList) {
		this.itemDtoList = itemDtoList;
	}
	
}
