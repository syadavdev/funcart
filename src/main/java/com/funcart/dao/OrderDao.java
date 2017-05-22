
package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Cart;
import com.funcart.domain.Item;
import com.funcart.domain.Order;

@Repository
public class OrderDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(rollbackOn=Exception.class)
	public boolean addOrderItems(Integer customerId,Integer itemId,Integer quantity)throws Exception{
		boolean flag = false;
		int check = em.createNativeQuery("INSERT INTO Orders(customerId,itemId,quantity) VALUES (?, ?, ?)")
					  .setParameter(1, customerId)
					  .setParameter(2, itemId)
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
			id = (Integer) em.createQuery(//"INSERT INTO 'Orders(customerId,customerName)"
				"Select o.id From Customer as o where o.email =:email")
		   		  		 .setParameter("email",email)
		   		  	//.executeUpdate();
		   		  		.getSingleResult();
		
		return id;
		
		}catch (Exception e) {
		e.printStackTrace();
		throw e;
	}

}
	public Cart checkExistCart(Integer itemId,Integer customerId)throws Exception{
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
	}
}


	