package com.funcart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int itemId;

	@Column
	private String name;
	
	@Column
	private String picName;
	
	@Column
	private String pricePerItem;
	
	public int getItemsId() {
		return itemId;
	}

	public void setItemsId(int itemsId) {
		this.itemId = itemsId;
	}

	public String getPrice() {
		return pricePerItem;
	}

	public void setPrice(String price) {
		this.pricePerItem = price;
	}

	public String getPicName() {
		return picName;
	}

	public int getId() {
		return id;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getName() {
		return name;
	}
}
