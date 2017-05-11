package com.funcart.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.ItemsDao;
import com.funcart.domain.dto.ItemDto;

@Service
public class ItemService {
	
	@Autowired
	private ItemsDao itemsDao;

	public List<ItemDto> getList()throws Exception{
		List<ItemDto> itemDto = null;
		if(itemsDao.getItemsList())
			itemDto = itemsDao.getItemListDto();
		return itemDto;
	}
}
