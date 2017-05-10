package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.CartDao;
import com.funcart.domain.dto.CartDto;

@Service
public class CartService {
	
	@Autowired
	private CartDao cartDao;
	
	public CartDto getCart(String email) throws Exception{	
		return cartDao.getItems(email);
	}
	
/*	public List<Item> addAndDelete(CartDto cartDto){
		List<Item> items = null;
		
		if(cartDto.getItemIdToAdd() > 0)
			if(cartDao.addItems(cartDto)){
				items = cartDao.getItems();
		
		else{
			if(cartDto.getItemIdToDelete() != 0)
				if(cartDao.deleteItems())
					items = cartDao.deleteItems();
		}
		return items;
	}*/
}
