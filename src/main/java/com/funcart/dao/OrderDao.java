
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
	public boolean addOrderItems(Integer customerId,long customerPhoneNumber,Integer quantity)throws Exception{
		boolean flag = false;
		long check = em.createNativeQuery("INSERT INTO Orders(customerId,customerPhoneNumber,quantity) VALUES (?, ?, ?)")
					  .setParameter(1, customerId)
					  .setParameter(2, customerPhoneNumber)
					  .setParameter(3, quantity)
					  .executeUpdate();
		if(check > 0)
			flag = true;
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getCartList(int customerId)throws Exception{
		List<Order> orderList = null;
		
		orderList = (List<Order>)em.createQuery("Select c From Cart as c where c.customerId = ?")
					  			 .setParameter(0, new Integer(customerId))
					  			 .getResultList();
		return orderList;
	}
	@SuppressWarnings("unchecked")
	public boolean  getCustomer(String email)throws Exception{
		List<Order> orderList = null;
		boolean flag=false;
		List result= em.createQuery("Select c From Customer as c where c.email = ?")
				.setParameter(0,email)
					  			// .setParameter(0, new Integer(customerId))
					  			 .getResultList();
		return true;
	}
	
	public Item getItem(int cartId)throws Exception{
		Item item = null;
		item = (Item) em.createQuery("Select i From Item i where i.itemId = ?")
				 		.setParameter(0, cartId)
				 		.getSingleResult();
		return item;
	}
	

	@Transactional(rollbackOn=Exception.class)
	public int getCustomerByEmail(String email) throws Exception {
	
		 String customerId=null;
		
		try
		{
			int id = 0;
			id = (Integer) em.createQuery("Select o.id  From Customer as o where o.email =:email")
		   		  		 .setParameter("email",email)
		   		  	//.executeUpdate();
		   		  		.getSingleResult();
		
		return id;
		
		}catch (Exception e) {
		e.printStackTrace();
		throw e;
	}

}
	/*public Cart checkExistCart(Integer itemId,Integer customerId)throws Exception{
		boolean flag = false;
		Cart cart = null;
		try{
			cart = (Cart)em.createQuery("Select c From Cart as cart cart.itemId = ? And cart.customerID = ?")
					 	   .setParameter(0, itemId)
					 	   .setParameter(1,customerId)
					 	   .getSingleResult();
		}catch(EntityNotFoundException e){
			cart = null;
		}catch(Exception e){
			throw e;
		}
		if(cart != null)
			flag = true;
		return cart;
	}*/
}


	