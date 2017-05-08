/*package com.funcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funcart.dao.PaymentDao;
import com.funcart.dao.service.CustomerService;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.AddressDto;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.PaymentDto;

@RestController
public class PaymentController {
	
	
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private Customer customer;
	
		
	
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	
	
	
	@RequestMapping(value="/payment",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity paymentMode(@RequestBody PaymentDto paymentDto) throws Exception{
		
		
		boolean pt=(Boolean) null;
	
		try{
			if(pt = paymentDao.paymentDetail(paymentDto))
					{
				httpStatus = httpStatus.ACCEPTED;
			}
			else{
				//httpStatus = httpStatus.UNAUTHORIZED;
				pt = new Customer() != null;
			}
		}catch(Exception e){
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
			pt = new Customer() != null;
		}
		
		return new ResponseEntity<Customer>(httpStatus);
	}
	

}
*/
