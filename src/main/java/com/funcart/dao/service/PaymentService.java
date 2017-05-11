/*package com.funcart.dao.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.AddressDao;
import com.funcart.dao.PaymentDao;
import com.funcart.domain.dto.PaymentDto;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentDao paymentDao;
	
	public boolean paymentMode(PaymentDto paymentDto) throws Exception{
		if(paymentDao.paymentMode(paymentDto))
			return true;
		else
			return false;
	}
}
*/