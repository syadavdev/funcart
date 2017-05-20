package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.LoginDao;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.customer.CustomerDto;
import com.funcart.domain.dto.customer.LoginDto;
import com.funcart.validator.Validator;

@Service
public class LoginService {
	
	@Autowired 
	private LoginDao loginDao;
	private CustomerDto customerDto;
	private String errorMsg;

	public boolean checkLogin(LoginDto loginDto) throws Exception{
		boolean flag = false,var1 = false,var2 = false;
		Customer customer = null;
		customerDto = null;
		if(Validator.phoneNumberValidate(loginDto.getEmailOrPhoneNumber())){
			if(loginDao.loginByPhone(loginDto)){
				var1 = true;
			}else{
				errorMsg = "PhoneNumber Invalid";
				return false;
			}
		}else if(Validator.emailValidate(loginDto.getEmailOrPhoneNumber())){
			if(loginDao.loginByEmail(loginDto)){
				var2 = true;
			}else{
				errorMsg = "Email Invalid";
				return false;
			}
		}else{
			errorMsg = "Invalid 'emailOrPhoneNumber' Detail";
		}
		
		if(var1 || var2){
			if((customer = loginDao.getCustomer()) != null){
				customerDto = new CustomerDto();
				customerDto.setName(customer.getUsername());
				customerDto.setPassword(customer.getPassword());
				customerDto.setEmail(customer.getEmail());
				customerDto.setPhoneNumber(customer.getPhoneNumber());
				customerDto.setBillingAddress(customer.getBillingAddress());
				customerDto.setShippingAddress(customer.getShippingAddress());
				flag = true;
			}
		}
		return flag;
	}
	
	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
