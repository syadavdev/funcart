/*package com.funcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.CartService;
import com.funcart.domain.Item;
import com.funcart.domain.dto.CartDto;

@RestController
public class CartController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private CartService cartService;

	@RequestMapping(value = "/cart",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public RequestEntity getKartItems(@RequestBody CartDto cartDto){
		try{
			List<Item> items = cartService.createCart();
			httpStatus = 
		}catch(Exception e){
			http
		}
		
		return RequestEntity<List<Item>>(cartService.CreateCart(),httpstatus);
	}	
	
	@RequestMapping(value = "/deleteCartItems",method=RequestMethod.POST,produces="MediaType.")
	public RequestEntity deleteKartItems(@RequestBody CartDto customer){
		return new RequestEntity<Cart>();
	}
	
	@RequestMapping(value = "/addCartItems",method=RequestMethod.POST,produces)
	public RequestEntity addKartItems(@RequestBody Customer customer){
		
	}
}*/