package com.funcart.domain.dto;

import java.util.List;

public class DeleteItemListDto {
	private String sellerName;
	private String sellerPasscode;
	private List<String> itemNameList;
	
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
	public List<String> getItemNameList() {
		return itemNameList;
	}
	public void setItemNameList(List<String> itemNameList) {
		this.itemNameList = itemNameList;
	}
}
