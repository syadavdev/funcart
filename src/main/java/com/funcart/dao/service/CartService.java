package com.funcart.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.CartDao;
import com.funcart.domain.Cart;
import com.funcart.domain.Item;
import com.funcart.domain.dto.ItemDto;
import com.funcart.domain.dto.cart.AddDeleteItemDto;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.cart.CartItemDto;

@Service
public class CartService {
	
	@Autowired
	private CartDao cartDao;
	
	private CartDto cartDto;

	public boolean getCart(String email) throws Exception{
		boolean flag = false;
		List<Cart> cartList = null;
		
		int customerId = cartDao.getCustomer(email);
		
		if(customerId > 0){
			cartList = cartDao.getCartList(customerId);
			if(!cartList.isEmpty() || cartList != null){
				cartDto = new CartDto();
				cartDto.setEmail(email);
				cartDto.setItemDtoList(getCartItems(cartList));
			}
		}
		if(!cartDto.getItemDtoList().isEmpty() && cartDto != null)
			flag = true;
		return flag;		
	}
	
	public List<CartItemDto> getCartItems(List<Cart> cartList)throws Exception{
		List<CartItemDto> cartItemDtoList = new ArrayList<CartItemDto>();
		
		if(!cartList.isEmpty()){
			Item item = null;
			for(Cart cart: cartList){
				CartItemDto cartItemDto = new CartItemDto();
				
				item = cartDao.getItem(cart.getItemId());
				
				cartItemDto.setItemName(item.getName());
				cartItemDto.setItemPicName(item.getPicName());
				cartItemDto.setItemId(item.getItemId());
				cartItemDto.setItemTotalPrice(item.getPrice() * cart.getQuantity());
				cartItemDto.setItemQty(cart.getQuantity());
				
				cartItemDtoList.add(cartItemDto);
			}
		}
		return cartItemDtoList;
	}
	
	
	public ItemDto addToCart(AddDeleteItemDto addDeleteItem) throws Exception{
		ItemDto itemDto = null;
		int customerId = 0;
		
		if(addDeleteItem.getItemId() > 0){
			customerId = cartDao.getCustomer(addDeleteItem.getEmail());
			if(customerId > 0){
				
			}
		}
		
		return itemDto;
	}
	
	public boolean deleteFromCart(AddDeleteItemDto addDeleteItem) throws Exception{
		boolean flag = false;
		int customerId = 0;
		
		if(addDeleteItem.getItemId() > 0){
			customerId = cartDao.getCustomer(addDeleteItem.getEmail());
			if(customerId > 0){
				if(cartDao.deleteItems(addDeleteItem.getItemId(), customerId))
					flag = true;
			}
		}
		return flag;
	}
	
	public CartDto getCartDto() {
		return cartDto;
	}

	public void setCartDto(CartDto cartDto) {
		this.cartDto = cartDto;
	}
}
