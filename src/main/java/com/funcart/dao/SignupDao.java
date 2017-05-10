package com.funcart.dao;

import java.util.List;

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
	
	SignupDto signupDtoObj;
	List<Customer> existCustomers;

	@Transactional(rollbackOn=Exception.class)
	public boolean saveCustomer(SignupDto signupDto)throws Exception{
		boolean flag = false;
		int result = 0; 
			
		result = em.createNativeQuery("INSERT INTO customer (username, password, email, phoneNumber,shippingAddress,billingAddress) "
						+ "VALUES (?, ?, ?, ?, ?, ?)")
					.setParameter(1, signupDto.getUsername())
					.setParameter(2, signupDto.getPassword())
					.setParameter(3, signupDto.getEmail())
					.setParameter(4, signupDto.getPhoneNumber())
					.setParameter(5, "")
					.setParameter(6, "")
					.executeUpdate();
		
		if(result > 0){
			flag = true;
		}		
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public boolean alreadyExist(SignupDto signupDto)throws Exception{
		boolean flag = false;
		existCustomers = null;
		existCustomers =  em.createQuery("from Customer as o where o.phoneNumber = ? or o.email = ?")
			     		   .setParameter(0, signupDto.getPhoneNumber())
						   .setParameter(1, signupDto.getEmail())
						   .getResultList();
		
		if(!existCustomers.isEmpty() && existCustomers != null)
			flag = true;
		return flag;
	}

	public SignupDto getSignupDtoObj() {
		return signupDtoObj;
	}
	public void setSignupDtoObj(SignupDto signupDtoObj) {
		this.signupDtoObj = signupDtoObj;
	}
	
	public List<Customer> getExistCustomers() {
		return existCustomers;
	}
	public void setExistCustomers(List<Customer> existCustomers) {
		this.existCustomers = existCustomers;
	}

}