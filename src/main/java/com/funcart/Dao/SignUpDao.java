package com.funcart.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;

@Repository
public class SignUpDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(rollbackOn=Exception.class)
	public boolean insertCustomer(Customer customer)throws Exception{
		boolean flag = false;
		if(checkSignupDetail(customer)){
			em.persist(customer);
			flag = true;
		}
		return flag;
	}
	
	public boolean checkSignupDetail(Customer customer){
		boolean flag = true;
		if(customer.getEmail().isEmpty() || customer.getPassword().isEmpty() || customer.getUsername().isEmpty() || 
			Long.toString(customer.getPhoneNumber()).length() != 10){
				flag = false;
		}
		else{
			flag = true;
		}
		return flag;
	}
}
