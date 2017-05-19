package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Cart;
import com.funcart.domain.Item;

@Repository
public class CartDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(rollbackOn=Exception.class)
	public boolean addCartItems(Integer customerId,Integer itemId,Integer quantity)throws Exception{
		boolean flag = false;
		int check = em.createNativeQuery("INSERT INTO cart(customerId,itemId,quantity) VALUES (?, ?, ?)")
					  .setParameter(1, customerId)
					  .setParameter(2, itemId)
					  .setParameter(3, quantity)
					  .executeUpdate();
		if(check > 0)
			flag = true;
		return flag;
	}
	
	@Transactional(value=TxType.REQUIRED, rollbackOn={Exception.class})
	public boolean deleteCart(Integer customerId)throws Exception{
		boolean flag = false;
		int check = -1;
		check = em.createQuery("Delete From Cart as cart Where cart.customerId = ?")
				  .setParameter(0, customerId)
				  .executeUpdate();
		if(check >= 0)
			flag = true;
		return flag;
	}
	
/*	public boolean checkExistCart(Integer itemId,Integer customerId)throws Exception{
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
	
	public int getCustomerByEmailPassword(String email,String password)throws Exception{
		int id = 0;
		id = (Integer) em.createQuery("Select id From Customer as o where o.email = ? and o.password = ?")
		   		  		 .setParameter(0,email)
		   		  		 .setParameter(1, password)
		   		  		 .getSingleResult();
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cart> getCartList(int customerId)throws Exception{
		List<Cart> cartList = null;
		
		cartList = (List<Cart>)em.createQuery("Select c From Cart as c where c.customerId = ?")
					  			 .setParameter(0, new Integer(customerId))
					  			 .getResultList();
		return cartList;
	}
	
	public Item getItem(int cartId)throws Exception{
		Item item = null;
		item = (Item) em.createQuery("Select i From Item i where i.itemId = ?")
				 		.setParameter(0, cartId)
				 		.getSingleResult();
		return item;
	}

	public int getCustomerByEmail(String email) {
		int id = 0;
		id = (Integer) em.createQuery("Select id From Customer as o where o.email = ?")
		   		  		 .setParameter(0,email)
		   		  		 .getSingleResult();
		return id;
	}
}
