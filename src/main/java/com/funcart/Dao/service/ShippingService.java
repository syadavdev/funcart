package com.funcart.Dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.Dao.LoginDao;
import com.funcart.Dao.ShippingDao;
import com.funcart.Dao.SignupDao;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.ShippingDto;
import com.funcart.domain.dto.SignupDto;

@Service
public class ShippingService {
	
	@Autowired
	private ShippingDao shipDao;
	
	public boolean saveCustomer(ShippingDto shipDto) throws Exception{
		if(shipDao.saveCustomer(shipDto))
			return true;
		else
			return false;
	}
}

