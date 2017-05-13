package com.funcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.CartService;
import com.funcart.domain.dto.cart.AddDeleteItemDto;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.error.ErrorResponse;
import com.funcart.validator.Validator;

@RestController
public class CartController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private CartService cartService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getCart",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getCartItems(@RequestParam String email){
		if(email.isEmpty() || !Validator.emailValidate(email))
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(400,"Invalid Email"),httpStatus);
		
		try{
			if(cartService.getCart(email))
				httpStatus = HttpStatus.OK;
			else{
				httpStatus = HttpStatus.NOT_FOUND;
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(404,"Items Not Found"),httpStatus);
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(500,"Internal Server Error"),httpStatus);
		}
		
		return new ResponseEntity<CartDto>(cartService.getCartDto(),httpStatus);
	}	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addCartItem",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addToCart(@RequestBody AddDeleteItemDto addAndDeleteItem){
		
		if(StringUtils.isEmpty(addAndDeleteItem.getEmail()) || !Validator.emailValidate(addAndDeleteItem.getEmail()))
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(400,"Invalid Email"),httpStatus);
		
		try{
			cartService.addToCart(addAndDeleteItem);
			httpStatus = HttpStatus.OK;
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(417,"Internal table"),httpStatus);
		}
		return new ResponseEntity<String>("sandeep",httpStatus);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deleteCartItem",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteToCart(@RequestBody AddDeleteItemDto addAndDeleteItem){
		
		if(StringUtils.isEmpty(addAndDeleteItem.getEmail()) || !Validator.emailValidate(addAndDeleteItem.getEmail()))
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(400,"Invalid Email"),httpStatus);
		String msg = "";
		try{
			if(cartService.deleteFromCart(addAndDeleteItem)){
				httpStatus = HttpStatus.OK;
				msg = "ItemId : "+ addAndDeleteItem.getItemId() +" Customer : "+addAndDeleteItem.getEmail()+"--Deleted";
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(417,"Expectation Failed"),httpStatus);
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(500,"Internal Server Error"),httpStatus);			
		}
		return new ResponseEntity<String>(msg,httpStatus);
	}
}