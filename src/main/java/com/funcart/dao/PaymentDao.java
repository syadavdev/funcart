/*package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;
import com.funcart.domain.dto.LoginDto;
import com.funcart.domain.dto.PaymentDto;


public class PaymentDao {
	
	@Repository
	public class paymentDao {
		
		@PersistenceContext
		private EntityManager em;
		
	
		public boolean paymentDetail(PaymentDto paymentDto) throws Exception{
			boolean flag = false;
			
			
		if (paymentDto.getPaymentBy())
		{
			System.out.println("payment Successful");
			flag=true;
		}
		else if(paymentDto.setPaymentBy("card"))
		{
			System.out.println("payment by card is Successful");
			flag=true;
		}
		else 
			System.out.println("failed");
			flag=false;
			
			return flag;
			
		}

	}

	public boolean paymentDetail(PaymentDto paymentDto) {
		// TODO Auto-generated method stub
		return false;
	}
}
*/