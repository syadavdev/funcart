package com.funcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.funcart.domain.Item;
import com.funcart.domain.dto.item.ItemDto;

@Repository
public class ItemsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private List<Item> itemList;

	public boolean getItems() throws Exception{
		boolean flag = false;
		itemList = null;
		
		itemList = em.createQuery("select i from Item i",Item.class).getResultList();
		
		if(!itemList.isEmpty() && itemList != null)
			flag = true;
		
		return flag;
	}
		
	@Transactional(value=TxType.REQUIRED, rollbackOn={Exception.class})
	public boolean insertItem(ItemDto itemDto){
		boolean flag = true;
		int check = 0;
		check = em.createNativeQuery("INSERT INTO item (name,itemId,picName,price) "
					+ "VALUES (?, ?, ?, ?)")
		    	  .setParameter(1, itemDto.getName())
				  .setParameter(2, new Integer(itemDto.getItemId()))
			      .setParameter(3, itemDto.getPicName())
				  .setParameter(4, new Double(itemDto.getPrice()))
				  .executeUpdate();
		
		if(check > 0)
			flag = true;
					
		return flag;
	}
	@SuppressWarnings("unchecked")
	public boolean checkItemExist(String itemName,Integer itemId)throws Exception{
		boolean flag = false;
		itemList = null;
		itemList = (List<Item>)em.createQuery("select i from Item as i where i.name = ? or i.itemId = ?")
					 			 .setParameter(0, itemName)
					 			 .setParameter(1, itemId)
					 			 .getResultList();
		
		if(!itemList.isEmpty() && itemList != null)
			flag = true;
		return flag;
	}
	
	@Transactional(value=TxType.REQUIRED, rollbackOn={Exception.class})
	public boolean deleteItem(String itemName){
		boolean flag = false;
		int check = 0;
		check = em.createQuery("Delete From Item As i Where i.name = ?")
				  .setParameter(0, itemName)
				  .executeUpdate();
		if(check > 0)
			flag = true;
		
		return flag;
	}
	
	public boolean checkItemNameExist(String name)throws Exception{
		boolean flag = false;
		String itemName = null;
		itemName = (String) em.createQuery("select name from Item as i where i.name = ?")
							 .setParameter(0, name)
							 .getSingleResult();
		if(!StringUtils.isEmpty(itemName)){
			flag = true;
		}
		return flag;
	}
	
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
}