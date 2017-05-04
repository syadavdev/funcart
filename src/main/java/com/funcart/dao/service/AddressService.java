package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.AddressDao;
import com.funcart.domain.dto.AddressDto;

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

