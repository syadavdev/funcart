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

	public boolean addItems(int customerId,int itemId)throws Exception{
		boolean flag = false;
		int check = em.createQuery("Insert Into Cart(customerId,itemId,quantity) Values(?,?,?)")
					  .setParameter(0, customerId)
					  .setParameter(1, itemId)
					  .executeUpdate();
		if(check > 0)
			flag = true;		
		return flag;
	}
	
	@Transactional(value=TxType.REQUIRED, rollbackOn={Exception.class})
	public boolean deleteItems(Integer itemId,Integer customerId)throws Exception{
		boolean flag = false;
		int check = 0;
		try{
			check = em.createQuery("Delete From Cart as cart Where cart.itemId = ? And cart.customerId = ?")
					  .setParameter(0, itemId)
					  .setParameter(1, customerId)
					  .executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(check > 0)
			flag = true;		
		return flag;
	}
	
	public int getCustomer(String email)throws Exception{
		int id = 0;
		id = (Integer) em.createQuery("Select id From Customer as o where o.email = ?")
		   		  		 .setParameter(0,email)
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
}
