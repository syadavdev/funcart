package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Item;

@Repository
public class ItemsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Item> getItemsList(){
		List<Item> itemsList = null;
		
		try{
			itemsList = em.createQuery("select i from Item i",Item.class).getResultList();
							
		}catch(Exception e){
			e.printStackTrace();
		}
		return itemsList;
	}
}