package com.funcart.dao.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.funcart.dao.CheckoutDao;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.PaymentDto;
import com.funcart.validator.Validator;

@Service
public class CheckoutService {
	
	@Autowired
	private CheckoutDao paymentDao;
	
	private String errorMsg;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public boolean paymentMode(PaymentDto paymentDto) throws Exception{
		
		
		if(Validator.phoneNumberValidate(paymentDto.getEmail())){
		
			if(paymentDao.paymentMode(paymentDto)){
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
	
}
