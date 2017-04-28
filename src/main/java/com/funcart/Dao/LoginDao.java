package com.funcart.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;

@Repository
public class LoginDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean checkLoginDetail(Customer customer) {
		  boolean flag = false;
		 
		  Query query = 
				em.createQuery("SELECT customer FROM Customer customer WHERE customer.username = :username AND customer.password = :password")
				.setParameter("username", customer.getUsername())
		  		.setParameter("password", customer.getPassword());
		  
		  try{
			  
			  Customer customerObj = (Customer) query.getSingleResult();
			  
			  if (customerObj == null)
				  flag = false;
			  else
				  flag = true;
			  
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		 return flag;
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
