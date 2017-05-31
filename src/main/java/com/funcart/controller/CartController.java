package com.funcart.controller;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.service.CartService;
import com.funcart.domain.dto.Response.ErrorResponse;
import com.funcart.domain.dto.Response.SuccessResponse;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.cart.UpdateCartDto;
import com.funcart.utility.JWTTokenGenerator;
import com.funcart.validator.Validator;

@RestController
public class CartController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private JWTTokenGenerator jwt;
	
	private class CustomerEmail{
		private String email;

		public String getEmail() {
			return email;
		}
		@SuppressWarnings("unused")
		public void setEmail(String email) {
			this.email = email;
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getCart",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getCartItems(@RequestParam CustomerEmail email,@RequestHeader String token,@RequestHeader String secret){
		String errorMsg = "Invalid Email";
		httpStatus = HttpStatus.BAD_REQUEST;
		if(!jwt.parseJWT(token,secret)){
			httpStatus = HttpStatus.UNAUTHORIZED;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Token Time Expire"),httpStatus);
		}else if(StringUtils.isEmpty(email) || !Validator.emailValidate(email.getEmail())){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Invalid Email"),httpStatus);
		}
		
		try{
			if(cartService.getCart(email.getEmail())){
				if(!cartService.getCartDto().getItemDtoList().isEmpty()){
					httpStatus = HttpStatus.OK;
					return new ResponseEntity<CartDto>(cartService.getCartDto(),httpStatus);
				}else{
					httpStatus = HttpStatus.NOT_FOUND;
					errorMsg = "Cart Not Found";
				}
			}else{
				httpStatus = HttpStatus.NOT_FOUND;
				errorMsg = "Error In Finding Customer";
			}
		}catch(NoResultException e){
			httpStatus = HttpStatus.NOT_FOUND;
			errorMsg = "Customer Not Found";
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			errorMsg = "Internal Server Error";
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updateCart",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateCart(@RequestBody UpdateCartDto updateCartDto,@RequestHeader String token,@RequestHeader String secret){
		String errorMsg = "Invalid Input";
		httpStatus = HttpStatus.BAD_REQUEST;
		if(!jwt.parseJWT(token,secret)){
			httpStatus = HttpStatus.UNAUTHORIZED;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Token Time Expire"),httpStatus);
		}else if(StringUtils.isEmpty(updateCartDto.getEmail())){
			errorMsg = "Empty Input Fields";
		}else if(!Validator.emailValidate(updateCartDto.getEmail())){
			errorMsg = "Invalid Email And Password";
		}else{
			try{
				if(cartService.checkCartInput(updateCartDto)){
					if(cartService.updateCart(updateCartDto)){
						httpStatus = HttpStatus.OK;
						return new ResponseEntity<SuccessResponse>(new SuccessResponse("Cart Update Successfully",httpStatus.value()),httpStatus);
					}else{
						httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
						errorMsg = cartService.getErrorMsg();
					}
				}else{
					httpStatus = HttpStatus.BAD_REQUEST;
					errorMsg = cartService.getErrorMsg();
				}
			}catch(Exception e){
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				errorMsg = "Internal Server Error";
				e.printStackTrace();
			}
		}
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
	}
}