package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;

import com.funcart.domain.dto.PaymentDto;
import com.funcart.domain.Customer;

@Repository
public class PaymentDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional(rollbackOn = Exception.class)
	public boolean paymentMode(PaymentDto paymentDto) throws Exception {
		boolean flag1 = false;
		
		if(paymentDto.getPaymentBy().equals("cash")){
			
	               flag1=true;
			paymentDto.setPaymentBy("payment successfull by cash");
		}
		else if
		(paymentDto.getPaymentBy().equals("card"))
		{ 
			paymentDto.setPaymentBy("payment successfull by card");
			flag1=true;
		}
		
		else 
			paymentDto.setPaymentBy("payment unsuccessfull");
			
		return flag1;
	}
		}

		

		/*boolean flag = false;
		Customer customer = new Customer();
		customer.setEmail(paymentDto.getEmail());
		customer.setPaymentBy(paymentDto.getPaymentBy());
		
	try {

		if (checkShipDetail(customer)) {

			int result = em
					.createQuery(
							"UPDATE Customer SET paymentBy= :paymentBy"	+ " where email = :email")
					.setParameter("email", customer.getEmail())
					.setParameter("paymentBy", customer.getPaymentBy())
					.executeUpdate();

			if (result >= 0)
				flag = true;
		}
	}
	catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
	
	return flag;
	
}

	public boolean checkShipDetail(Customer customer) {
		boolean flag = false;
		if (!customer.getPaymentBy().isEmpty() || customer.getPaymentBy().equals("cash")) {
			flag = true;
		} else {
			flag = true;
		}
		return flag;*/
	
	
	

