package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.LoginDao;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.CustomerDto;
import com.funcart.domain.dto.LoginDto;
import com.funcart.validator.Validator;

@Service
public class LoginService {
	
	@Autowired 
	private LoginDao loginDao;
	private CustomerDto customerDto;
	
	public boolean checkLogin(LoginDto loginDto) throws Exception{
		boolean flag = false,var1 = false,var2 = false;
		Customer customer = null;
		customerDto = null;
		if(var1 = Validator.phoneNumberValidate(loginDto.getName())){
			loginDao.loginByPhone(loginDto);
		}else if(var2 = Validator.emailValidate(loginDto.getName())){
			loginDao.loginByEmail(loginDto);	  
		}
		
		if(var1 || var2){
			if((customer = loginDao.getCustomer()) != null){
				customerDto = new CustomerDto();
				customerDto.setUsername(customer.getUsername());
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
}
