/*package com.funcart.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int totalItem;
	
	@Column
	private Set<Item> itemsList;

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public Set<Item> getItemsList() {
		return itemsList;
	}

	public void setItemsList(Set<Item> itemsList) {
		this.itemsList = itemsList;
	}

	public int getId() {
		return id;
	}
}*/