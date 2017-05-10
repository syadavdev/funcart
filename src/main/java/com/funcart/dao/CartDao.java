package com.funcart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Cart;
import com.funcart.domain.Item;
import com.funcart.domain.dto.CartDto;
import com.funcart.validator.Validator;

@Repository
public class CartDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private Query query;
	private int customerId;
	private List<Cart> cartList;
	List<Item> items;

	public List<Item> getItems(String email)throws Exception{
		query = null;
		if(getCustomer(email) && getCartList() && getItemList()){
		}	
		else{
			items = new ArrayList<Item>();
		}
		return items;
	}
	
	public boolean addItems(CartDto cartDto)throws Exception{
		boolean flag = false;
		
		return flag;
	}
	
	public boolean deleteItems()throws Exception{
		boolean flag = false;
		
		return flag;
	}
	
	public boolean getCustomer(String email)throws Exception{
		
		customerId = 0;
		query = null;
		boolean flag = false;
			  
		if(Validator.emailValidate(email)){
			query = em.createQuery("Select id From Customer as o where o.email = ?")
			   		  .setParameter(0,email);
			flag = true;
		}
		if(flag){
		  try{
			  customerId = (Integer) query.getSingleResult();
	  		}catch (Exception e) {
		  		flag = false;
		  	}
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public boolean getCartList()throws Exception{
		boolean flag = false;
		cartList = null;
		query = null;
		if(customerId > 0){
			query = em.createQuery("Select c From Cart as c where c.customerId = ?")
					  .setParameter(0, new Integer(customerId));
			flag = true;
		}
		if(flag){
			try{
				cartList = (List<Cart>) query.getResultList();
			}catch(Exception e){
				flag = false;
				throw e;
			}
		}
		return flag;
	}
	
	public boolean getItemList()throws Exception{
		boolean flag = false;
		Item item = null;
		items = new ArrayList<Item>();
		query = null;
		if(!cartList.isEmpty()){
			for(Cart cart: cartList){
				try{
					item = (Item) em.createQuery("Select i From Item i where i.itemId = ?")
							 		.setParameter(0, cart.getItemId())
							 		.getSingleResult();
				}catch(Exception e){
					flag = false;
					e.printStackTrace();
				}
				items.add(item);
			}
			flag = true;
		}	
		
		return flag;
	}
}
