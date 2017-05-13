package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.AddressDao;
import com.funcart.dao.CheckoutDao;

import com.funcart.domain.dto.CheckoutDto;

@Service
public class CheckoutService {

	@Autowired
	private CheckoutDao checkDao;

	

	public CheckoutDto checkCustomer(String email) throws Exception {
		
		return(checkDao.checkout(email));
		
	}
}
			
			
			
	
	
