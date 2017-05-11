package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Item;

@Repository
public class ItemsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private List<Item> itemList;

	public boolean getItems() throws Exception{
		boolean flag = false;
		itemList = null;
		
		itemList = em.createQuery("select i from Item i",Item.class).getResultList();
		
		if(!itemList.isEmpty() && itemList != null)
			flag = true;
		
		return flag;
	}
	
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
}