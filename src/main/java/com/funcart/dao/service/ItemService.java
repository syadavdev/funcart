package com.funcart.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.funcart.dao.ItemsDao;
import com.funcart.domain.Item;
import com.funcart.domain.dto.item.ItemDto;
import com.funcart.validator.Validator;

@Service
public class ItemService {
	
	@Autowired
	private ItemsDao itemsDao;
	
	private List<ItemDto> itemDtoList;
	
	private String errorMsg;

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
	
	public boolean insertItemList(List<ItemDto> itemList)throws Exception{
		boolean flag = false;
		for(ItemDto item : itemList){
			if(itemsDao.insertItem(item)){
				flag = true;
			}
			else{
				flag = false;
				errorMsg = "Error In Inserting";
				break;
			}
		}
		return flag;
	}
	
	public boolean deleteItemList(List<String> itemNames)throws Exception{
		boolean flag = false;
		for(String name : itemNames){
			if(itemsDao.deleteItem(name)){
				flag = true;
			}else{
				flag = false;
				break;
			}
		}	
		return flag;
	}
	
	public boolean checkItemList(List<ItemDto> itemList) throws Exception{
		boolean flag = false;
		List<String> itemName = new ArrayList<String>();
		List<Integer> itemId = new ArrayList<Integer>();
		
		for(ItemDto item : itemList){
			if(!checkItemDetail(item)){
				flag = false;
				break;
			}else{
				try{
					if(itemName.contains(item.getName())){
						errorMsg = "Duplicate ItemName '"+item.getName()+"'";
						flag = true;
						break;
					}else if(itemId.contains(item.getItemId())){
						errorMsg = "Duplicate ItemId '"+item.getItemId()+"'";
						flag = true;
						break;
					}else if(itemsDao.checkItemExist(item.getName(), item.getItemId())){
						flag = true;
						errorMsg = "ItemId : "+item.getItemId()+" Already Exist";
						break;
					}else{
						flag = false;
					}
				}catch(NoResultException e){
					flag = false;
				}
				itemName.add(item.getName());
				itemId.add(item.getItemId());
			}
		}
		return flag;
	}
	
	public boolean checkItemDetail(ItemDto item){
		boolean flag = false;
		if(!(item.getItemId() > 0) || !(item.getPrice() > 0) || StringUtils.isEmpty(item.getName()) 
				|| StringUtils.isEmpty(item.getPicName())){
			errorMsg = "Items Feilds Empty";
		}else if(!Validator.nameValidate(item.getName())){
			errorMsg = "Item Name Invalid : '" + item.getName() + "'";
		}else if(!Validator.imageNameValidate(item.getPicName())){
			errorMsg = "ItemPicName Invalid : '"+ item.getPicName() +"'";
		}else{
			flag = true;
		}
		return flag;
	}
	
	public boolean checkItemNames(List<String> itemNames)throws Exception{
		boolean flag = false;
		List<String> itemNameList = new ArrayList<String>();
		for(String name : itemNames){
			if(StringUtils.isEmpty(name)){
				flag = false;
				errorMsg = "ItemName Empty";
				break;
			}else if(!Validator.nameValidate(name)){
				flag = false;
				errorMsg = "ItemName Invalid";
				break;
			}else{
				try{
					if(itemNameList.contains(name)){
						flag = false;
						errorMsg = "Duplicate Names : '"+name+"'";
						break;
					}
					if(itemsDao.checkItemNameExist(name)){
						itemNameList.add(name);
						flag = true;
					}else{
						errorMsg = "Item Not Exist : '"+name+"'";
						flag = false;
						break;
					}
				}catch(Exception e){
					errorMsg = "Item Not Exist : '"+name+"'";
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	public List<ItemDto> getItemDtoList() {
		return itemDtoList;
	}
	public void setItemDtoList(List<ItemDto> itemDtoList) {
		this.itemDtoList = itemDtoList;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
