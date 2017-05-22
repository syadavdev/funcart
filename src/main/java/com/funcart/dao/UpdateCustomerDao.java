package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;

@Repository
public class UpdateCustomerDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(rollbackOn = Exception.class)
	public boolean updateCustomer(String generatedQuery) throws Exception {
		boolean flag = false;
		int result = 0;

		result = em.createQuery(generatedQuery).executeUpdate();

		if(result > 0)
			flag = true;
		return flag;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public boolean changePassword(String email,String newPassword,String oldPassword) throws Exception {
		boolean flag = false;
		int result = 0;

		result = em.createQuery("Update Customer Set password = :newPassword Where email = :email And password = :oldPassword")
				   .setParameter("newPassword", newPassword)
				   .setParameter("email", email)
				   .setParameter("oldPassword", oldPassword)
				   .executeUpdate();

		if(result > 0)
			flag = true;
		return flag;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public boolean changeEmail(String newEmail,String oldEmail,String password) throws Exception {
		boolean flag = false;
		int result = 0;

		result = em.createQuery("Update Customer Set email = :newEmail Where email = :oldEmail And password = :password")
				   .setParameter("newEmail", newEmail)
				   .setParameter("oldEmail", oldEmail)
				   .setParameter("password", password)
				   .executeUpdate();

		if(result > 0)
			flag = true;
		return flag;
	}
	
	public boolean checkCustomer(String email,String password)throws Exception{
		boolean flag = false;
		Customer customer = null;
		customer = (Customer) em.createQuery("Select c From Customer As c Where c.email = ? And c.password = ?")
								.setParameter(0, email)
								.setParameter(1, password)
								.getSingleResult();
		if(customer != null)
			flag = true;
		return flag;
	}
	
	public boolean checkCustomerNumber(Long phoneNumber){
		boolean flag = false;
		Customer customer = null;
		customer = (Customer) em.createQuery("Select c From Customer As c Where c.phoneNumber = ?")
								.setParameter(0, phoneNumber)
								.getSingleResult();
		
		if(customer != null)
			flag = true;
		return flag;
	}
	
	public boolean checkCustomerEmail(String email){
		boolean flag = false;
		Customer customer = null;
		customer = (Customer) em.createQuery("Select c From Customer As c Where c.email = ?")
								.setParameter(0, email)
								.getSingleResult();
		
		if(customer != null)
			flag = true;
		return flag;
	}
}