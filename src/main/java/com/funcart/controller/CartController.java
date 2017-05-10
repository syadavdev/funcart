package com.funcart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.CartService;
import com.funcart.domain.Item;

@RestController
public class CartController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private CartService cartService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/cart",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getCartItems(@RequestParam String email){
		List<Item> items = null;
		try{
			items = cartService.getCart(email);
			if(items != null)
				httpStatus = HttpStatus.OK;
			else{
				httpStatus = HttpStatus.NOT_FOUND;
				items = new ArrayList<Item>();
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			items = new ArrayList<Item>();
		}
		
		return new ResponseEntity<List<Item>>(items,httpStatus);
	}	
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addAndDeleteCartItems",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteCartItems(@RequestBody CartDto cartDto){
		List<Item> items = null;
		try{
			items = cartService.addAndDelete(cartDto);
			httpStatus = HttpStatus.OK;
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Item>>(items,httpStatus);
	}*/
}