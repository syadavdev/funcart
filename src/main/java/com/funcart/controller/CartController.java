package com.funcart.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.CartService;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.cart.CartItemDto;

@RestController
public class CartController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private CartService cartService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getCart",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getCartItems(@RequestParam String email){
		CartDto cartDto = new CartDto();
		try{
			cartDto = cartService.getCart(email);
			if(!cartDto.getItemDtoList().isEmpty())
				httpStatus = HttpStatus.OK;
			else{
				httpStatus = HttpStatus.NOT_FOUND;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			cartDto.setEmail(email);
			cartDto.setItemDtoList(new ArrayList<CartItemDto>());
		}
		
		return new ResponseEntity<CartDto>(cartDto,httpStatus);
	}	
	
/*	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deleteCartItem",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteToCart(@RequestBody AddDeleteItemDto addAndDeleteItem){
		CartDto cartDto = new CartDto();
		try{
			cartDto() = cartService.addAndDelete(addAndDelete);
			httpStatus = HttpStatus.OK;
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Item>>(items,httpStatus);
	}
	
	@RequestMapping(value = "/addCartItem",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE,consume, )
	public ResponseEntity addToCart(@ResponseBody AddDeleteItemDto addAndDelete)*/
}