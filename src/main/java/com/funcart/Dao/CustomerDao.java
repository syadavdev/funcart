package com.funcart.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;

@Repository
public class CustomerDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(rollbackOn=Exception.class)
	public boolean insertCustomer(Customer customer)throws Exception{
		em.persist(customer);
		return true;
	}

	public boolean checkingCustomer(Customer customer){
		Query query = em.createQuery("SELECT username,password FROM customer");

		return true;
	}

}
