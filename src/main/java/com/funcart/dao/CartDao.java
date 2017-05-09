/*package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.funcart.domain.Customer;
import com.funcart.domain.Item;
import com.funcart.domain.dto.CartDto;
import com.funcart.validator.Validator;

@Repository
public class CartDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private Customer customer;
	List<Item> items;

	public List<Item> getItems(String email)throws Exception{
		Query query = null;
		if(this.getCustomer(email)){
			
		}
		return items;
	}
	
	public boolean getCustomer(String email)throws Exception{
		boolean flag = false;
		Query query = null;	
		if(!StringUtils.isEmpty(email) && Validator.emailValidate(email)){
			query = em.createQuery("Select c from Customer as c where c.email = ?")
					  .setParameter(0, email);
			flag = true;
		}else{
			flag = false;
		}
		try{
			customer = (Customer) query.getSingleResult();
		}catch(Exception e){
			throw e;
		}
		return flag; 
	}
	
	public boolean addItems(CartDto cartDto)throws Exception{
		boolean flag = false;
		
		return flag;
	}
	
	public boolean deleteItems()throws Exception{
		boolean flag = false;
		
		return flag;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
*/