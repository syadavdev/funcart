package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.funcart.domain.Customer;
import com.funcart.domain.dto.LoginDto;
import com.funcart.validator.Validator;

@Repository
public class LoginDao {
	
	@PersistenceContext
	private EntityManager em;
	
	//@Transactional(rollbackOn=Exception.class)
	public Customer checkLoginDetail(LoginDto loginDto) throws Exception{
		  
		  Customer ct = new Customer();
		  Query query = null;
		  String hql = "Select o from Customer as o where o.password = ?"; 
		  
		  if(!StringUtils.isEmpty(loginDto.getPassword()) && Validator.passwordValidate(loginDto.getPassword())){
			  
			  if(Validator.phoneNumberValidate(loginDto.getName())){
				  hql = hql + " and o.phoneNumber = ?";
				  query = em.createQuery(hql)
						  	.setParameter(0,loginDto.getPassword())
						  	.setParameter(1,Long.parseLong(loginDto.getName()));
			  }
			  
			  else if(Validator.emailValidate(loginDto.getName())){
				  hql = hql + " and o.email = ?";
				  query = em.createQuery(hql)
						  	.setParameter(0,loginDto.getPassword())
						  	.setParameter(1,loginDto.getName());	  
				  
			  }else{
				  ct = null;
			  }
		  }else{
			  ct = null;			  
		  }
		  
		  try{
			  ct = (Customer) query.getSingleResult();
		  }catch (Exception e) {
			  loginDto.setName("Exception Catches in login");
			  throw e;
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