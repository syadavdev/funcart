package com.funcart.dao.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.funcart.dao.CheckoutDao;

import com.funcart.domain.dto.CheckoutDto;
import com.funcart.validator.Validator;

@Service
public class CheckoutService {
	
	@Autowired
	private CheckoutDao checkDao;

	
	private String errorMsg;
	
	public boolean paymentMode(CheckoutDto paymentDto) throws Exception{
		
		
		if(Validator.emailValidate(paymentDto.getEmail())){
		
			if(checkDao.customerCheckout(paymentDto)){
				return true;
			}else{
				errorMsg = "Email Invalid";
				return false;
			}
		}else{
			errorMsg = "Invalid 'email' Detail";
	
		
		//if(paymentDao.paymentMode(paymentDto))
			
			return false;
	}
	
	}
	
	


public CheckoutDao getCheckDao() {
	return checkDao;
}
public void setCheckDao(CheckoutDao checkDao) {
	this.checkDao = checkDao;
}


public String getErrorMsg() {
	return errorMsg;
}
public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
}
}
