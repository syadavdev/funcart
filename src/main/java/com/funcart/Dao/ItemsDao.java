package com.funcart.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Items;

@Repository
public class ItemsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Items> getItemsList(){
		List<Items> itemsList = null;
		
		try{
			itemsList = em.createQuery("select i from Items i").getResultList();
							
		}catch(Exception e){
			e.printStackTrace();
		}
		return itemsList;
	}
}