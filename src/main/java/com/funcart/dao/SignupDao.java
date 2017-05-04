package com.funcart.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;
import com.funcart.domain.dto.SignupDto;

@Repository
public class SignupDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(rollbackOn=Exception.class)
	public boolean saveCustomer(SignupDto customerDto)throws Exception{
		
		boolean flag = false;
		
		Customer customer = new Customer();
		customer.setUsername(customerDto.getUsername());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setBillingAddress("");
		customer.setShippingAddress("");
		
		try{
			
			if(checkSignupDetail(customer)){
			
				int result = em.createNativeQuery("INSERT INTO customer (username, password, email, phoneNumber,shippingAddress,billingAddress) "
								+ "VALUES (?, ?, ?, ?, ?, ?)")
								.setParameter(1,customer.getUsername())
								.setParameter(2, customer.getPassword())
								.setParameter(3, customer.getEmail())
								.setParameter(4, customer.getPhoneNumber())
								.setParameter(5, customer.getShippingAddress())
								.setParameter(6, customer.getShippingAddress())
								.executeUpdate();
				
					if(result > 0)
						flag = true;
				}
			}catch(Exception e){
				throw e;
			}
		return flag;
	}
	
	public boolean checkSignupDetail(Customer customer){
		boolean flag = false;
		if(customer.getEmail().isEmpty() || customer.getPassword().isEmpty() || customer.getPassword().length() < 8 || customer.getUsername().isEmpty() || 
			Long.toString(customer.getPhoneNumber()).length() != 10){
				flag = false;
		}
		else{
			flag = true;
		}
		return flag;
	}
}