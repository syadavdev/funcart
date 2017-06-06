
package com.funcart.controller;

import java.util.List;

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
import com.funcart.dao.service.OrderService;
import com.funcart.domain.dto.CustomerEmailDto;
import com.funcart.domain.dto.Response.ErrorResponse;
import com.funcart.domain.dto.Response.SuccessResponse;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.cart.UpdateCartDto;
import com.funcart.domain.dto.order.CustomerMailDto;
import com.funcart.domain.dto.order.OrderDto;
import com.funcart.domain.dto.order.OrderItemDto;
import com.funcart.utility.JWTTokenGenerator;
import com.funcart.validator.Validator;

@RestController
public class OrderController {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private JWTTokenGenerator jwt;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/createOrder",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createOrder(@RequestBody CustomerMailDto customerEmail,@RequestHeader String token,@RequestHeader String secret){
		String errorMsg = "Invalid Email";
		httpStatus = HttpStatus.BAD_REQUEST;
		if(!jwt.parseJWT(token,secret)){
			httpStatus = HttpStatus.UNAUTHORIZED;
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Token Time Expire"),httpStatus);
		}else if(StringUtils.isEmpty(customerEmail) || !Validator.emailValidate(customerEmail.getEmail())){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Invalid Email"),httpStatus);
		}
		
		try{
			if(orderService.getOrderDetail(customerEmail.getEmail())){
				if(!orderService.getOrderDto().getOrderItemDtoList().isEmpty()){
					//if(!orderService.getOrderDto().getOrdercustomerDtoList().isEmpty())
					//if(!orderService.getOrderDto().getOrdercustomerDto().isEmpty())
					httpStatus = HttpStatus.OK;
					return new ResponseEntity<OrderDto>(orderService.getOrderDto(),httpStatus);
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