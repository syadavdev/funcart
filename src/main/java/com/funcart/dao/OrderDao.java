
package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Order;

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
					+ "from cart inner join customer on customer.id=cart.customerid"
					+ " inner join item on item.itemid=cart.itemid  where customer.email =:email")
					.setParameter("email", email)
		   		  	 .executeUpdate();
			if(i > 0){
				
					orderList = (List<Order>)em.createNativeQuery("Select * from orders where customerid=(select id from customer  where customer.email =:email)",Order.class)
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
	@SuppressWarnings("rawtypes")
	@Transactional(rollbackOn=Exception.class)
	public List<Order> getCustomerEmail(String email) throws Exception {
		List<Order> orderList = null;
		boolean flag=false;
		 try{
				
			int i = em.createNativeQuery("delete from cart where customerid=(select id from customer  where customer.email =:email)")
						              .setParameter("email", email)						              
							          .executeUpdate();
			if(i>0)	
				flag=true;
		 }catch(Exception e) { 
			e.printStackTrace();
	 		throw e;
	 	}
		return orderList;
	}
}



	