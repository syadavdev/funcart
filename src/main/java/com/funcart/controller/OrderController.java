
package com.funcart.controller;

import javax.persistence.NoResultException;

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
import com.funcart.dao.service.OrderService;
import com.funcart.domain.dto.Response.ErrorResponse;
import com.funcart.domain.dto.Response.SuccessResponse;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.cart.UpdateCartDto;
import com.funcart.domain.dto.order.OrderDto;
import com.funcart.validator.Validator;

@RestController
public class OrderController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private OrderService orderService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/checkoutt",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getCartItems(@RequestParam String email){
		String errorMsg = "Invalid Email";
		if(StringUtils.isEmpty(email) || !Validator.emailValidate(email)){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(400,"Invalid Email"),httpStatus);
		}
		
		try{
			if(orderService.getCart(email)){
				if(!orderService.getOrderDto().getOrderDtoList().isEmpty()){
					httpStatus = HttpStatus.OK;
					return new ResponseEntity<OrderDto>(httpStatus);
				}else{
					httpStatus = HttpStatus.NOT_FOUND;
					errorMsg = "Order Not Found";
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
}