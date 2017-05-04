/*package com.funcart.controller;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.domain.Customer;

@RestController
public class CartController {

	@RequestMapping(value = "/kartItems",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public RequestEntity getKartItems(@RequestBody Customer customer){
		
		
		return new RequestEntity<Cart>();
		
	}	
	
	@RequestMapping(value = "/deleteKartItems",method=RequestMethod.POST,produces="MediaType.")
	public RequestEntity deleteKartItems(@RequestBody Customer customer){
		return new RequestEntity<Cart>();
	}
	
	@RequestMapping(value = "/addKartItems",method=RequestMethod.POST,produces)
	public RequestEntity addKartItems(@RequestBody Customer customer){
		
	}
}*/