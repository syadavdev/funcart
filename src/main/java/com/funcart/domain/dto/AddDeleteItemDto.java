package com.funcart.domain.dto;

public class AddDeleteItemDto {
	
	private String email;
	private int addItemId;
	private int deleteItemId;
	
	public int getAddItemId() {
		return addItemId;
	}
	public void setAddItemId(int addItemId) {
		this.addItemId = addItemId;
	}
	public int getDeleteItemId() {
		return deleteItemId;
	}
	public void setDeleteItemId(int deleteItemId) {
		this.deleteItemId = deleteItemId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
