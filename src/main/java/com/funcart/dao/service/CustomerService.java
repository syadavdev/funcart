package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.LoginDao;
import com.funcart.dao.SignupDao;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.SignupDto;

@Service
public class CustomerService {
	
	@Autowired
	private SignupDao signUpDao;
	
	@Autowired 
	private LoginDao loginDao;

	public SignupDto saveCustomer(SignupDto signupDto) throws Exception{
				
		if(signUpDao.checkSignupDetail(signupDto))
			if(!signUpDao.alreadyExist(signupDto))
				signUpDao.saveCustomer(signupDto);		
		
		return signUpDao.getSignupDtoObj();
	}
	
	public boolean matching(SignupDto obj1,SignupDto obj2){
		boolean flag = false;
		
		if(obj1.getEmail() == obj2.getEmail())
			flag = true;
		if(obj1.getPassword() == obj2.getPassword())
			flag = true;
		if(obj1.getPhoneNumber() == obj2.getPhoneNumber())
			flag = true;
		if(obj1.getUsername() == obj2.getUsername())
			flag = true;
		
		return flag;
	}
	
	public Customer checkLogin(LoginDto loginDto) throws Exception{
		return loginDao.checkLoginDetail(loginDto);
	}
}
