
package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Cart;
import com.funcart.domain.Customer;
import com.funcart.domain.Item;
import com.funcart.domain.Order;

@Repository
public class OrderDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(rollbackOn=Exception.class)
	public boolean getCustomerByEmail(String email) throws Exception {
		boolean flag = false;
		 String customerId=null;
		
		try
		{
			int id = 0;
			id = (Integer) em.createQuery("Select customer.id,customer.username,customer.phonenumber,customer.billingaddress,customer.shippingaddress,cart.quantity,item.itemid,item.name,item.price "
					+ " from cart inner join customer on customer.id=cart.customerid"
					+ " inner join item on item.itemid=cart.itemid  where customer.email =:email")
		   		  		 .setParameter("email",email)
		   		  	     //.executeUpdate();
		   		  		.getSingleResult();
			flag = true;
		;
		
		}catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
		return flag;
}
}


	