package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;
import com.funcart.domain.dto.LoginDto;

@Repository
public class LoginDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(rollbackOn=Exception.class)
	public Customer checkLoginDetail(LoginDto loginDto) throws Exception{
		  
		  Customer ct = new Customer();
		  Query query = null;
		  
		  if(loginDto.getPassword() != null){
			  if(loginDto.getUsername() != null){
				  query = em.createQuery("Select o from Customer as o where o.username = ? and o.password = ?")
						  	.setParameter(0,loginDto.getUsername())
						  	.setParameter(1,loginDto.getPassword());
			  
			  }else if(loginDto.getEmail() != null){
				  query = em.createQuery("Select o from Customer as o where o.email = ? and o.password = ?")
						  	.setParameter(0,loginDto.getEmail())
						  	.setParameter(1,loginDto.getPassword());
			  
			  }else if(Long.toString(loginDto.getPhoneNumber()).length() == 10){
				  query = em.createQuery("Select o from Customer as o where o.phoneNumber = ? and o.password = ?")
						  	.setParameter(0,loginDto.getPhoneNumber())
						  	.setParameter(1,loginDto.getPassword());
			  
			  }else{
				  ct = null;
			  }
		  }else{
			  ct = null;			  
		  }
		  
		  try{
			  ct = (Customer) query.getSingleResult();
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		 return ct;
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
