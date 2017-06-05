package com.funcart.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.CheckoutDao;
import com.funcart.dao.service.CheckoutService;


import com.funcart.domain.dto.CheckoutDto;
import com.funcart.domain.dto.Response.ErrorResponse;

import com.funcart.utility.JWTTokenGenerator;
import com.funcart.validator.Validator;


@RestController
public class CheckoutController {
	
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	@SuppressWarnings("unused")
	@Autowired
	private CheckoutDao paymentDao;
	
	@Autowired
	private CheckoutService checkoutService;
	
	@Autowired
	private JWTTokenGenerator jwt;
	
@SuppressWarnings({ "rawtypes", "static-access" })

@RequestMapping(value = "/checkout",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity savePayment(@RequestBody CheckoutDto paymentDto,@RequestHeader String token,@RequestHeader String secret)throws Exception{
	
	String errorMsg = "Invalid Email";
	httpStatus = HttpStatus.BAD_REQUEST;
	if(!jwt.parseJWT(token,secret)){
		httpStatus = HttpStatus.UNAUTHORIZED;
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Token Time Expire"),httpStatus);
	}else if(StringUtils.isEmpty(paymentDto.getEmail())){
		errorMsg = "Empty Input Fields";
	}else if(!Validator.emailValidate(paymentDto.getEmail())){
		errorMsg = "Invalid Email And Password";
	}else{
	
	@SuppressWarnings("unused")
	CheckoutDto payObj = null;

	try {
		
		if (checkoutService.paymentMode(paymentDto)) {
			httpStatus = httpStatus.OK;
			payObj = paymentDto;
			return new ResponseEntity<CheckoutDto>(paymentDto, httpStatus);
			//return new ResponseEntity<CheckoutDto>(checkoutService.getCheckDto(),httpStatus);
		} else
		{
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			errorMsg = checkoutService.getErrorMsg();
		}
	} catch (Exception e) {
		httpStatus = HttpStatus.NOT_ACCEPTABLE;
		payObj = new CheckoutDto();
		e.printStackTrace();
	}
	}
	return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
}
}