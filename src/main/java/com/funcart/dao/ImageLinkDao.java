package com.funcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class ImageLinkDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private String picLink;
	
	public boolean getImageLink(String picName){
		boolean flag = true;
		picLink = null;
		picLink = (String) em.createQuery("Select picLink From ImageLinks where picName = :picName")
						  .setParameter("picName", picName)
						  .getSingleResult();
		if(!StringUtils.isEmpty(picLink))
			flag = true;
		return flag;
	}

	public String getPicLink() {
		return picLink;
	}
	public void setPicLink(String picLink) {
		this.picLink = picLink;
	}	
}
