package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;
import com.funcart.domain.dto.customer.LoginDto;

@Repository
public class LoginDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private Customer customer;
	
	//@Transactional(rollbackOn=Exception.class)
	public boolean loginByPhone(LoginDto loginDto) throws Exception{
		  boolean flag = false;
		  customer = null;		  
		  customer = (Customer) em.createQuery("Select o from Customer as o where o.password = ? and o.phoneNumber = ?")
				  				.setParameter(0,loginDto.getPassword())
				  				.setParameter(1,Long.parseLong(loginDto.getEmailOrPhoneNumber()))
				  				.getSingleResult(); 
		  if(customer != null)
			  flag = true;
		  
		  return flag;
	 }
	
	public boolean loginByEmail(LoginDto loginDto)throws Exception{
		  boolean flag = false;
		  customer = null;
		  customer = (Customer) em.createQuery("Select o from Customer as o where o.password = ? and o.email = ?")
				  					.setParameter(0,loginDto.getPassword())
				  					.setParameter(1,loginDto.getEmailOrPhoneNumber())
				  					.getSingleResult();
		  if(customer != null)
			  flag = true;
		  
		  return flag;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/*	 public boolean checkingCustomer(Customer customer) {
	  boolean flag = false;
	  try {
		  Session session = (Session) em.getDelegate();
		  Criteria cr = session.createCriteria(Customer.class);
		  cr.add(Restrictions.eq("username", customer.getUsername()));
		  cr.add(Restrictions.eq("password", customer.getPassword()));
		  cr.setFirstResult(1);
		  List<Customer> results = cr.list();
		  Query query = 
					em.createQuery("FROM Customer customer WHERE customer.username = :username AND customer.password = :password")
				  	.setParameter("username", customer.getUsername())
				  	.setParameter("password", customer.getPassword());
		  
		  	customer = (Customer) query.getSingleResult();
		  	if (customer.getUsername().equalsIgnoreCase(customer.getUsername())
				  	&& customer.getPassword().equals(customer.getPassword())) {
			  flag = true;
		  }
	  }catch (Exception e) {
		 e.printStackTrace();
	  }
	  
	  return flag;
	}*/
}