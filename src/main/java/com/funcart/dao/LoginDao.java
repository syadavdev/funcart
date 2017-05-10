package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.funcart.domain.Customer;
import com.funcart.domain.dto.CustomerDto;
import com.funcart.domain.dto.LoginDto;
import com.funcart.validator.Validator;

@Repository
public class LoginDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private CustomerDto customerDto;
	
	//@Transactional(rollbackOn=Exception.class)
	public boolean checkLoginDetail(LoginDto loginDto) throws Exception{
		  boolean flag = false;
		  Customer customer = new Customer();
		  Query query = null;
		  String hql = "Select o from Customer as o where o.password = ?"; 
		  
		  if(!StringUtils.isEmpty(loginDto.getPassword()) && Validator.passwordValidate(loginDto.getPassword())){
			  
			  if(Validator.phoneNumberValidate(loginDto.getName())){
				  hql = hql + " and o.phoneNumber = ?";
				  query = em.createQuery(hql)
						  	.setParameter(0,loginDto.getPassword())
						  	.setParameter(1,Long.parseLong(loginDto.getName()));
			  }else if(Validator.emailValidate(loginDto.getName())){
				  hql = hql + " and o.email = ?";
				  query = em.createQuery(hql)
						  	.setParameter(0,loginDto.getPassword())
						  	.setParameter(1,loginDto.getName());	  
			  }else{
				  customer.setUsername("Not Valid");
			  }
		  }else{
			  customer.setPassword("Not Valid");
		  }
		  
		  if(!"Not Valid".equals(customer.getPassword()) && !"Not Valid".equals(customer.getUsername())){
			  try{
				  customer = (Customer) query.getSingleResult();
			  }catch (Exception e) {
				  loginDto.setName("Exception Catches");
				  throw e;
			  }
			  customerDto = new CustomerDto();
			  customerDto.setUsername(customer.getUsername());
			  customerDto.setPassword(customer.getPassword());
			  customerDto.setEmail(customer.getEmail());
			  customerDto.setPhoneNumber(customer.getPhoneNumber());
			  customerDto.setBillingAddress(customer.getBillingAddress());
			  customerDto.setShippingAddress(customer.getShippingAddress());
			  
			  flag = true;
		  }
		 return flag;
	 }

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
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