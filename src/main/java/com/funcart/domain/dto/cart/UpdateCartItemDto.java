package com.funcart.domain.dto.cart;

public class UpdateCartItemDto {
	private int ItemId;
	private int ItemQty;
	
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	public int getItemQty() {
		return ItemQty;
	}
	public void setItemQty(int itemQty) {
		ItemQty = itemQty;
	}
}
