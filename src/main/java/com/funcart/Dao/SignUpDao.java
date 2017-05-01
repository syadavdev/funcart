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
			try{
				int result = em.createNativeQuery("INSERT INTO customer (username, password, email, phoneNumber)"
									+ " VALUES (?, ?, ?, ?)")
							   .setParameter(1,customer.getUsername())
							   .setParameter(2, customer.getPassword())
							   .setParameter(3, customer.getEmail())
							   .setParameter(4, customer.getPhoneNumber())
							   .executeUpdate();
				flag = true;
			}catch(Exception e){
				e.printStackTrace();
				flag = false;
			}
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
