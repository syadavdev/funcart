package com.funcart.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.ShippingDao;
import com.funcart.domain.dto.ShippingDto;

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

