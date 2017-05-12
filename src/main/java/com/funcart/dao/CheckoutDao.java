package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.funcart.domain.Checkout;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.CheckoutDto;
import com.funcart.validator.Validator;

@Repository
public class CheckoutDao {
	
	private Checkout checkout;

	@PersistenceContext
	private EntityManager em;

	@Transactional(rollbackOn = Exception.class)
	public boolean checkCustomer(CheckoutDto checkDto) throws Exception {
		boolean flag = false;
		Customer customer = new Customer();
		customer.setEmail(checkDto.getEmail());
		customer.getUsername();
		
		
		try {

			if (checkout(customer)) {

	
				int result = em
						.createQuery("INSERT INTO Checkout(customerId,customerName)"
								+"Select o.id,o.username From Customer as o where o.email =:email")
						
						.setParameter("email", customer.getEmail())
						//.setParameter("customerId", checkout.getCustomerId())
						//.setParameter("customerName", checkout.getCustomerName())
				
				.executeUpdate();
						
				if (result > 0)
					
					flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	public boolean checkout(Customer customer) {
		boolean flag = false;
		if (customer.getEmail().isEmpty()) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}
}
	
		/*query = null;
		try {
			if(getCustomer(customer)) { 
				
				query = em
						//.createQuery("Select username From Customer as o where o.email =:email")
						.createQuery("INSERT INTO checkout(CustomerId,CustomerName)"
								+ "SELECT id,username FROM customer"
								+ "where email = :email")
						.setParameter("email", customer.getEmail());
						
				
					flag = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	public boolean getCustomer(Customer customer) {
		boolean flag = false;
		if (customer.getEmail().isEmpty() ) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	
	}

}*/
