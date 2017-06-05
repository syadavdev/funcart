
package com.funcart.dao;

import java.util.ArrayList;
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
import com.funcart.domain.dto.cart.CartItemDto;

@Repository
public class OrderDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackOn=Exception.class)
	public List<Order> getCustomerByEmail(String email) throws Exception {
		List<Order> orderList = null;
		boolean flag=false;
		 try
		{
			//Object id = null;
			int i = em.createNativeQuery("insert into orders(customerid,customername,customerphonenumber,billingaddressid,shippingaddressid,paymentmode,quantity,itemid,itemname,price)"
					+"Select customer.id,customer.username,customer.phonenumber,customer.billingaddress,customer.shippingaddress,customer.paymentby,cart.quantity,item.itemid,item.name,item.price "
					+ "from Cart inner join customer on customer.id=cart.customerid"
					+ " inner join Item on item.itemid=cart.itemid  where customer.email =:email")
					.setParameter("email", email)
		   		  	 .executeUpdate();
			if(i > 0){
				
					orderList = (List<Order>)em.createNativeQuery("Select * from Orders where customerid=(select id from customer  where customer.email =:email)",Order.class)
							              .setParameter("email", email)
								  			 .getResultList();
					
				}
			flag=true;
		}catch (Exception e) { 
		e.printStackTrace();
		throw e;
	}
		return orderList;
		
}
}


	