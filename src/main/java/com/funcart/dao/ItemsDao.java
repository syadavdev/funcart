package com.funcart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Item;
import com.funcart.domain.dto.ItemDto;

@Repository
public class ItemsDao {
	
	@PersistenceContext
	private EntityManager em;
	private List<ItemDto> itemListDto;

	public boolean getItemsList() throws Exception{
		
		boolean flag = true;
		List<Item> itemsList = null;
		itemListDto = new ArrayList<ItemDto>();
		
		try{
			itemsList = em.createQuery("select i from Item i",Item.class).getResultList();				
		}catch(Exception e){
			itemsList = null;
			throw e;
		}
		
		if(!itemsList.isEmpty() && itemsList != null){
			ItemDto itemDto = null;
			for(Item item: itemsList){
				itemDto = new ItemDto();
				itemDto.setItemId(item.getItemId());
				itemDto.setName(item.getName());
				itemDto.setPicName(item.getPicName());
				itemDto.setPrice(item.getPrice());
				try{
					itemListDto.add(itemDto);
				}catch(Exception e){
					e.printStackTrace();
				}
			}	
			flag = true;
		}	
		return flag;
	}
	
	public List<ItemDto> getItemListDto() {
		return itemListDto;
	}
	public void setItemListDto(List<ItemDto> itemListDto) {
		this.itemListDto = itemListDto;
	}
}