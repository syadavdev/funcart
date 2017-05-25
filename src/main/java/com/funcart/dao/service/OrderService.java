package com.funcart.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.CartDao;
import com.funcart.dao.OrderDao;
import com.funcart.domain.Cart;
import com.funcart.domain.Customer;
import com.funcart.domain.Item;
import com.funcart.domain.Order;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.cart.CartItemDto;
import com.funcart.domain.dto.cart.UpdateCartDto;
import com.funcart.domain.dto.cart.UpdateCartItemDto;
import com.funcart.domain.dto.customer.CustomerDto;
import com.funcart.domain.dto.order.OrderDto;
import com.funcart.domain.dto.order.OrderItemDto;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	private OrderDto orderDto;
	private CustomerDto customerDto;
	private String errorMsg;
	
	public boolean getCart(String email) throws Exception{
		boolean flag = false;
		List<Order> orderList = null;
		
		int customerId = orderDao.getCustomerByEmail(email);
		Integer itemId =0;
		//Cart cart = orderDao.checkExistCart(itemId,customerId);
		
		if(customerId > 0){
				orderList = orderDao.getCartList(customerId);
			//orderList =  orderDao.getCustomer(customerId);
			if(!orderList.isEmpty() || orderList != null){
				orderDto = new OrderDto();
				orderDto.setEmail(email);
				//orderDto.setOrderDtoList(getCartItems(orderList));
				orderDto.setOrderDtoList(getCustomerItems(orderList));
				flag = true;
			}
		}
		return flag;		
	}
	@SuppressWarnings("null")
	public List<OrderItemDto> getCustomerItems(List<Order> orderList)throws Exception{
		List<OrderItemDto> orderItemDtoList = new ArrayList<OrderItemDto>();		
		if(!orderList.isEmpty()){
			//int customerId = orderDao.getCustomerByEmail(orderDto.getEmail());
			//OrderItemDto orderItemDto = new OrderItemDto();
			//orderDao.addOrderItems(customerId, orderItemDto.getPhoneNumber(), orderItemDto.getItemQty());
			Customer customer = null;
			Integer customerId =0;
			String email=null;
			//for(@SuppressWarnings("unused") Order order: orderList){
				
				
				 orderDao.getCustomer(email);
					OrderItemDto orderItemDto = new OrderItemDto();
					orderItemDto.setPhoneNumber(customer.getPhoneNumber());
					
	orderDao.addOrderItems(customerId, orderItemDto.getPhoneNumber(), orderItemDto.getItemQty());
 orderDto.setOrderDtoList(getCartItems(orderList));
				 
				 OrderItemDto orderItemDto1 = new OrderItemDto();
				orderItemDto1.setName(customer.getUsername());
				
				
				//orderDao.addOrderItems(customerId, orderItemDto1.getItemId(), orderItemDto1.getItemQty());
				//orderItemDtoList.add(orderItemDto1);
	
			
			
			
				
		
		}
		return orderItemDtoList;
	}
	
	
	public List<OrderItemDto> getCartItems(List<Order> orderList)throws Exception{
		List<OrderItemDto> orderItemDtoList = new ArrayList<OrderItemDto>();		
		if(!orderList.isEmpty()){
		int customerId = orderDao.getCustomerByEmail(orderDto.getEmail());
			OrderItemDto orderItemDto = new OrderItemDto();
			orderDao.addOrderItems(customerId, orderItemDto.getPhoneNumber(), orderItemDto.getItemQty());
			Item item = null;

			for(Order order: orderList){
				
				OrderItemDto orderItemDto1 = new OrderItemDto();
				item = orderDao.getItem(order.getItemId());
				orderItemDto1.setItemName(item.getName());
				
				orderItemDto1.setItemId(item.getItemId());
				orderItemDto1.setItemTotalPrice(item.getPrice() * order.getQuantity());
				orderItemDto1.setItemQty(order.getQuantity());
				//orderDao.addOrderItems(customerId, orderItemDto1.getItemId(), orderItemDto1.getItemQty());
				orderItemDtoList.add(orderItemDto1);
				//OrderItemDto orderItemDto = new OrderItemDto();
				//orderDao.addOrderItems(customerId, orderItemDto.getPhoneNumber(), orderItemDto.getItemQty());
			
			
			
				
			}
		}
		return orderItemDtoList;
	
	}
	public OrderDto getOrderDto() {
		return orderDto;
	}
	public void setOrderDto(OrderDto orderDto) {
		this.orderDto = orderDto;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	



}
