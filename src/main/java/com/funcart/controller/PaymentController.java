package com.funcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.PaymentDao;
import com.funcart.domain.Customer;
import com.funcart.domain.Item;
import com.funcart.domain.dto.PaymentDto;
import com.funcart.domain.dto.SignupDto;

@RestController
public class PaymentController {
	
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	@Autowired
	private PaymentDao paymentDao;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/payment",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity savePayment(@RequestBody PaymentDto paymentDto) throws Exception{
		PaymentDto payObj=null;
		try
		{
			if(paymentDao.paymentMode(paymentDto))
			{
				httpStatus = httpStatus.CREATED;
			}else{
				httpStatus = httpStatus.EXPECTATION_FAILED;
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PaymentDto>(payObj,httpStatus);
			
	}
}