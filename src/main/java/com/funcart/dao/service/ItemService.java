package com.funcart.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.ItemsDao;
import com.funcart.domain.Item;
import com.funcart.domain.dto.ItemDto;

@Service
public class ItemService {
	
	@Autowired
	private ItemsDao itemsDao;
	
	private List<ItemDto> itemDtoList;

	public boolean getList()throws Exception{
		boolean flag = false;
		itemDtoList = null;
		List<Item> itemList = null;
		itemDtoList = null;
		
		if(itemsDao.getItems()){
			itemDtoList = new ArrayList<ItemDto>();
			itemList = itemsDao.getItemList();
			for(Item item : itemList){
				ItemDto itemDto = new ItemDto();
				itemDto.setItemId(item.getItemId());
				itemDto.setName(item.getName());
				itemDto.setPicName(item.getPicName());
				itemDto.setPrice(item.getPrice());
				itemDtoList.add(itemDto);
			}
		}
		
		if(itemDtoList != null && !itemDtoList.isEmpty())
			flag = true;
			
		return flag;
	}

	public List<ItemDto> getItemDtoList() {
		return itemDtoList;
	}
	public void setItemDtoList(List<ItemDto> itemDtoList) {
		this.itemDtoList = itemDtoList;
	}
}
