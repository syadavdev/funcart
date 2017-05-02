package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CartDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void getItems(){
		
	}
}
