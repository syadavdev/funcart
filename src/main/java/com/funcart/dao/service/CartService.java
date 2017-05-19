package com.funcart.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.CartDao;
import com.funcart.domain.Cart;
import com.funcart.domain.Item;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.cart.CartItemDto;
import com.funcart.domain.dto.cart.UpdateCartDto;
import com.funcart.domain.dto.cart.UpdateCartItemDto;

@Service
public class CartService {
	
	@Autowired
	private CartDao cartDao;
	
	private CartDto cartDto;
	private String errorMsg;

	public boolean getCart(String email) throws Exception{
		boolean flag = false;
		List<Cart> cartList = null;
		int customerId = cartDao.getCustomerByEmail(email);
		if(customerId > 0){
				cartList = cartDao.getCartList(customerId);
			if(!cartList.isEmpty() || cartList != null){
				cartDto = new CartDto();
				cartDto.setEmail(email);
				cartDto.setItemDtoList(getCartItems(cartList));
				flag = true;
			}
		}
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
	
	public boolean updateCart(UpdateCartDto updateCartDto)throws Exception{
		boolean flag = false;
		int customerId = 0;
		
		customerId = cartDao.getCustomerByEmailPassword(updateCartDto.getEmail(),updateCartDto.getPassword());
		cartDao.deleteCart(customerId);
		
		List<UpdateCartItemDto> cartItemList = updateCartDto.getUpdateCartItem();
		
		for(UpdateCartItemDto cartItem : cartItemList){
			if(cartDao.addCartItems(customerId, cartItem.getItemId(), cartItem.getItemQty())){
					flag = true;
			}else{
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public boolean checkCartInput(UpdateCartDto updateCartDto) throws Exception{
		boolean flag = false;
		int customerId = 0;
		try{
			customerId = cartDao.getCustomerByEmailPassword(updateCartDto.getEmail(),updateCartDto.getPassword());
		}catch(NoResultException e){
			customerId = 0;
		}
		if(customerId > 0){
			if(checkCartItems(updateCartDto.getUpdateCartItem())){
				flag = true;
			}
		}else{
			errorMsg = "Customer Not Exist";
		}

		return flag;
	}
	
	public boolean checkCartItems(List<UpdateCartItemDto> cartItemsList) throws Exception{
		boolean flag = false;
		ArrayList<Integer> itemList = new ArrayList<Integer>();
		for(UpdateCartItemDto cartItem: cartItemsList){
			if(cartItem.getItemId() >= 0 && cartItem.getItemQty() >= 0){
				if(!itemList.contains(cartItem.getItemId())){
					try{
						Item item = cartDao.getItem(cartItem.getItemId());
						if(item != null){
							itemList.add(item.getItemId());
							flag = true;
						}else{
							break;
						}
				
					}catch(NoResultException e){
						errorMsg = "Invalid ItemId";
						flag = false;
						break;
					}
				}else{
					errorMsg = "Duplicate CartItems";
					flag = false;
					break;
				}
			}else{
				flag = false;
				errorMsg = "Invalid CartItemId";
				break;
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
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
