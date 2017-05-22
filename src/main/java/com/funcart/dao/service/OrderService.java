package com.funcart.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcart.dao.CartDao;
import com.funcart.dao.OrderDao;
import com.funcart.domain.Cart;
import com.funcart.domain.Item;
import com.funcart.domain.Order;
import com.funcart.domain.dto.cart.CartDto;
import com.funcart.domain.dto.cart.CartItemDto;
import com.funcart.domain.dto.cart.UpdateCartDto;
import com.funcart.domain.dto.cart.UpdateCartItemDto;
import com.funcart.domain.dto.order.OrderDto;
import com.funcart.domain.dto.order.OrderItemDto;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	private OrderDto orderDto;
	private String errorMsg;
	
	public boolean getCart(String email) throws Exception{
		boolean flag = false;
		List<Order> orderList = null;
		
		int customerId = orderDao.getCustomerByEmail(email);
		Integer itemId =0;
		Cart cart = orderDao.checkExistCart(itemId,customerId);
		
		if(customerId > 0){
				orderList = orderDao.getCartList(customerId);
			if(!orderList.isEmpty() || orderList != null){
				orderDto = new OrderDto();
				orderDto.setEmail(email);
				orderDto.setOrderDtoList(getCartItems(orderList));
				flag = true;
			}
		}
		return flag;		
	}
	public List<OrderItemDto> getCartItems(List<Order> orderList)throws Exception{
		List<OrderItemDto> orderItemDtoList = new ArrayList<OrderItemDto>();

		
		
		if(!orderList.isEmpty()){
			int customerId = orderDao.getCustomerByEmail(orderDto.getEmail());
			Item item = null;
			for(Order order: orderList){
				OrderItemDto orderItemDto1 = new OrderItemDto();
				
				item = orderDao.getItem(order.getItemId());
				
				orderItemDto1.setItemName(item.getName());
				
				orderItemDto1.setItemId(item.getItemId());
				orderItemDto1.setItemTotalPrice(item.getPrice() * order.getQuantity());
				orderItemDto1.setItemQty(order.getQuantity());
				orderDao.addOrderItems(customerId, orderItemDto1.getItemId(), orderItemDto1.getItemQty());
				orderItemDtoList.add(orderItemDto1);
			OrderItemDto orderItemDto = new OrderItemDto();
			
			orderDao.addOrderItems(customerId, orderItemDto.getItemId(), orderItemDto.getItemQty());
			
				
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
