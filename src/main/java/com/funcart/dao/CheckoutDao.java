package com.funcart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.funcart.domain.Cart;
import com.funcart.domain.Checkout;
import com.funcart.domain.Customer;
import com.funcart.domain.Item;
import com.funcart.domain.dto.CheckaddressDto;
import com.funcart.domain.dto.CheckoutDto;
import com.funcart.domain.dto.CustomerDto;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.cart.CartItemDto;
import com.funcart.validator.Validator;

@Repository
public class CheckoutDao {
	
	@PersistenceContext
	private EntityManager em;
	private Query query;
	private int customerId;
	private String email;
	private String customerBillAddress;
	private String customerShipAddress;
	
	private List<Cart> cartList;
	private List<Customer> customerList;
	private List<CheckoutDto> checkList;
	private List<CheckaddressDto> addressList;
	private List<CartItemDto> itemDtoList;
	private CheckoutDto checkDto;

	public CheckoutDto checkout(String email)throws Exception{
		checkDto = new CheckoutDto();
		checkDto.setEmail(email);
		if(getCustomer(email) && getCartList() && getItemList() && getAddressList()){
			checkDto.setItemDtoList(itemDtoList);
		}	
		else{
			checkDto.setItemDtoList(new ArrayList<CartItemDto>());
		}
		return checkDto;
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
public boolean getItemList()throws Exception{
	boolean flag = false;
	itemDtoList = new ArrayList<CartItemDto>();
	query = null;
	if(!cartList.isEmpty()){
		Item item = null;
		for(Cart cart: cartList){
			CartItemDto itemDto = new CartItemDto();
			try{
				item = (Item) em.createQuery("Select i From Item i where i.itemId = ?")
						 		.setParameter(0, cart.getItemId())
						 		.getSingleResult();
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
			
			itemDto.setItemName(item.getName());
			itemDto.setItemId(item.getItemId());
			itemDto.setItemTotalPrice(item.getPrice() * cart.getQuantity());
			itemDto.setItemQty(cart.getQuantity());
			
			itemDtoList.add(itemDto);
		}
		flag = true;
	}
	return flag;
}

@SuppressWarnings("unchecked")
public boolean getAddressList()throws Exception{
	boolean flag = false;

addressList = new ArrayList<CheckaddressDto>();
query = null;


Customer customer = new Customer();
customer.setEmail(checkDto.getEmail());


customer.getUsername();
if(Check(customer)){
				try{
			int result =  (Integer) em.createQuery("Select o.shippingAddress,o.billingAddress From Customer as o "
					+ "where o.email =:email")
					
					 		.setParameter("email", customer.getEmail())
					 		
			
			               .executeUpdate();
			
			if (result > 0)
				flag = true;
			
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		
				CheckaddressDto itemDto = new CheckaddressDto();
		itemDto.setShippingAddress(customer.getShippingAddress());
		itemDto.setBillingAddress(customer.getBillingAddress());
		
		
		addressList.add(itemDto);
	
	flag = true;
}
return flag;
}


private boolean Check(Customer customer) {
	
	boolean flag = false;
	if (customer.getEmail().isEmpty()) {
		flag = false;
	} else {
		flag = true;
	}
	return flag;
}

	

}
