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
	
	public Customer checkLoginDetail(Customer customer) {
		  Customer ct = null;
		 
		  Query query = 
				em.createQuery("Select o from Customer as o where o.username = ? and o.password = ?")
					.setParameter(0,customer.getUsername())
					.setParameter(1,customer.getPassword());
		  
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
