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
	public boolean saveCustomer(AddressDto shipDto) throws Exception {

		boolean flag = false;

		Customer customer = new Customer();
		customer.setEmail(shipDto.getEmail());
		customer.setBillingAddress(shipDto.getBillingAddress());
		customer.setShippingAddress(shipDto.getShippingAddress());

		try {

			if (checkShipDetail(customer)) {

				int result = em
						.createQuery(
								"UPDATE Customer SET billingAddress = :billingAddress,shippingAddress = :shippingAddress"
										+ " where email = :email")
						.setParameter("email", customer.getEmail())
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