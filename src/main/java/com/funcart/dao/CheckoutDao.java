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
public class CheckoutDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional(rollbackOn = Exception.class)
	public boolean paymentMode(PaymentDto paymentDto) throws Exception {
		boolean flag = false;
	
		Customer customer = new Customer();
		customer.setEmail(paymentDto.getEmail());
		customer.setBillingAddress(paymentDto.getBillingaddress());
		customer.setShippingAddress(paymentDto.getShippingaddress());
		customer.setPaymentBy(paymentDto.getPaymentBy());
		
	try {

		if (checkShipDetail(customer)) {

			int result = em
					.createQuery(
							"UPDATE Customer SET paymentBy= :paymentBy, billingAddress = :billingAddress,shippingAddress = :shippingAddress"	+ " where email = :email")
					.setParameter("email", customer.getEmail())
					.setParameter("paymentBy", customer.getPaymentBy())
					.setParameter("shippingAddress", customer.getShippingAddress())
					.setParameter("billingAddress", customer.getBillingAddress())
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
			if (!customer.getPaymentBy().isEmpty() || customer.getPaymentBy().equals("card")) {
				flag = true;
		} else {
			flag = false;
		} }else
		{
			flag=false;
		}
	
		return flag;
	}
	
}


