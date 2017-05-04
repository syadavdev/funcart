package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.LoginDao;
import com.funcart.dao.AddressDao;
import com.funcart.dao.SignupDao;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.AddressDto;
import com.funcart.domain.dto.SignupDto;

@Service
public class AddressService {
	
	@Autowired
	private AddressDao shipDao;
	
	public boolean saveCustomer(AddressDto shipDto) throws Exception{
		if(shipDao.saveCustomer(shipDto))
			return true;
		else
			return false;
	}
}

