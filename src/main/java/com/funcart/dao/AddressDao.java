package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.AddressDto;

@Repository
public class AddressDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(rollbackOn = Exception.class)
	public boolean saveCustomer(AddressDto addressDto) throws Exception {
		boolean flag = false;
		int result = 0;

		result = em.createQuery("UPDATE Customer SET billingAddress = :billingAddress,shippingAddress = :shippingAddress"
										+ " where email = :email")
					.setParameter("email", addressDto.getEmail())
					.setParameter("shippingAddress", addressDto.getShippingAddress())
					.setParameter("billingAddress", addressDto.getBillingAddress()).executeUpdate();

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
}