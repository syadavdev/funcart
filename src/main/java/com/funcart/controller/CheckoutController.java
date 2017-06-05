package com.funcart.controller;

import java.util.List;

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
import com.funcart.domain.Customer;
import com.funcart.domain.Item;

import com.funcart.domain.dto.PaymentDto;
import com.funcart.domain.dto.Response.ErrorResponse;
import com.funcart.utility.JWTTokenGenerator;
import com.funcart.validator.Validator;


@RestController
public class CheckoutController {
	
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	@Autowired
	private CheckoutDao paymentDao;
	
	@Autowired
	private CheckoutService checkoutService;
	
	@Autowired
	private JWTTokenGenerator jwt;
	
@SuppressWarnings({ "rawtypes", "static-access" })

@RequestMapping(value = "/checkout",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity savePayment(@RequestBody PaymentDto paymentDto)throws Exception{
	@SuppressWarnings("unused")
	String errorMsg = "Invalid Email";
		httpStatus = HttpStatus.UNAUTHORIZED;
	 if(StringUtils.isEmpty(paymentDto) || !Validator.emailValidate(paymentDto.getEmail())){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Invalid Email"),httpStatus);
	}else
	{
	/*String errorMsg = "Invalid Input";
	httpStatus = HttpStatus.BAD_REQUEST;
	if(!jwt.parseJWT(token,secret)){
		httpStatus = HttpStatus.UNAUTHORIZED;
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),"Token Time Expire"),httpStatus);
	}else if(StringUtils.isEmpty(paymentDto.getEmail())){
		errorMsg = "Empty Input Fields";
	}else if(!Validator.emailValidate(paymentDto.getEmail())){
		errorMsg = "Invalid Email And Password";
	}else{*/
	
	PaymentDto payObj = null;

	try {
		
		if (checkoutService.paymentMode(paymentDto)) {
			httpStatus = httpStatus.OK;
			payObj = paymentDto;
		} else
		{
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			errorMsg = checkoutService.getErrorMsg();
			
			return new ResponseEntity<PaymentDto>(paymentDto, httpStatus);
		}
	} catch (Exception e) {
		httpStatus = HttpStatus.NOT_ACCEPTABLE;
		payObj = new PaymentDto();
	}

	return new ResponseEntity<PaymentDto>(payObj, httpStatus);
}
	//return new ResponseEntity<ErrorResponse>(new ErrorResponse(httpStatus.value(),errorMsg),httpStatus);
}
}