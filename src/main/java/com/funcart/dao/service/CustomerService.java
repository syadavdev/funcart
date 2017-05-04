package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.funcart.dao.LoginDao;
import com.funcart.dao.SignupDao;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.LoginDto;
=======
import com.funcart.dao.SignupDao;
>>>>>>> 4fec1dfecf4331738fc9ce93498685263e309151
import com.funcart.domain.dto.SignupDto;

@Service
public class CustomerService {
	
	@Autowired
	private SignupDao signUpDao;
	
	public boolean saveCustomer(SignupDto signupDto) throws Exception{
		if(signUpDao.saveCustomer(signupDto))
			return true;
		else
			return false;
	}
}
