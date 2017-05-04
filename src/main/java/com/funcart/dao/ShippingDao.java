package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.funcart.domain.Customer;
import com.funcart.domain.dto.ShippingDto;

@Repository
public class ShippingDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(rollbackOn = Exception.class)
	public boolean saveCustomer(ShippingDto shipDto) throws Exception {

		boolean flag = false;

		Customer customer = new Customer();
		customer.setUsername(shipDto.getUserName());
		customer.setBillingAddress(shipDto.getBillingAddress());
		customer.setShippingAddress(shipDto.getShippingAddress());

		try {

			if (checkShipDetail(customer)) {

				int result = em
						.createQuery(
								"UPDATE Customer SET billingAddress = :billingAddress,shippingAddress = :shippingAddress"
										+ " where username = :username")
						.setParameter("username", customer.getUsername())
						.setParameter("shippingAddress", customer.getShippingAddress())
						.setParameter("billingAddress", customer.getBillingAddress()).executeUpdate();

				if (result > 0)
					flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	public boolean checkShipDetail(Customer customer) {
		boolean flag = false;
		if (customer.getShippingAddress().isEmpty() || customer.getBillingAddress().isEmpty()) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}
}