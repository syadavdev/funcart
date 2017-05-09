package com.funcart.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.ItemsDao;
import com.funcart.domain.Item;

@Service
public class ItemService {
	
	@Autowired
	private ItemsDao itemsDao;

	public List<Item> getList()throws Exception{
		return itemsDao.getItemsList();
	}
}
