package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;

import com.funcart.domain.dto.PaymentDto;

	
	@Repository
	public class PaymentDao {
		
		@PersistenceContext
		private EntityManager em;
		
		

	public boolean paymentMode(PaymentDto paymentDto) {
		
		
			boolean flag = false;
		
			
			
			if(paymentDto.getPaymentBy().equals("cash"))
		flag=true;
			else if
			(paymentDto.getPaymentBy().equals("card"))
			{ flag=true;
			}
			
			else 
				flag=false;
			return flag;
		
	}
	}
