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
import com.funcart.domain.dto.order.OrderCustomerDto;
import com.funcart.domain.dto.order.OrderDto;
import com.funcart.domain.dto.order.OrderItemDto;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	private OrderDto orderDto;
	private String errorMsg;

	public boolean getOrderDetail(String email) throws Exception {
		boolean flag = false;
		List<Order> orderList = null;

		orderList = orderDao.getCustomerByEmail(email);
		if (orderList != null) {
		
			if (!orderList.isEmpty() || orderList != null) {
				orderDto = new OrderDto();
				orderDto.setEmail(email);
				orderDto.setOrderItemDtoList(getCartItems(orderList));
				orderDto.setOrdercustomerDtoList(getCustomerItems(orderList));
				
				flag = true;

			}
		}
		return flag;

	}

	public List<OrderItemDto> getCartItems(List<Order> orderList) throws Exception {
		List<OrderItemDto> orderItemDtoList = new ArrayList<OrderItemDto>();

		if (!orderList.isEmpty()) {

			try {

				for (Order order : orderList) {

					OrderItemDto orderItemDto = new OrderItemDto();

					orderItemDto.setItemName(order.getItemName());
					orderItemDto.setItemId(order.getItemId());
					orderItemDto.setItemTotalPrice(order.getPrice() * order.getQuantity());
					orderItemDto.setItemQty(order.getQuantity());
					orderItemDtoList.add(orderItemDto);
					orderDto.setPaymentMode(order.getPaymentMode());
					orderDto.setOrderId(order.getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return orderItemDtoList;
	}

/*	public List<OrderCustomerDto> getCustomerItems(List<Order> orderList) throws Exception {
		List<OrderCustomerDto> ordercustomerDtoList = new ArrayList<OrderCustomerDto>();

		if (!orderList.isEmpty()) {
			Item item = null;
			try {

				for (Order order : orderList) {

					OrderCustomerDto ordercustomerDto = new OrderCustomerDto();

					ordercustomerDto.setName(order.getCustomerName());
					//ordercustomerDto.setEmail(order.getCustomerId());
					ordercustomerDto.setBillingaddress(order.getBillingAddressId());
					ordercustomerDto.setShippingAddress(order.getShippingAddressId());
					ordercustomerDto.setPhoneNumber(order.getCustomerPhoneNumber());

					ordercustomerDtoList.add(ordercustomerDto);

				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return ordercustomerDtoList;
	}*/
	
	
public OrderCustomerDto getCustomerItems(List<Order> orderList) throws Exception {
		OrderCustomerDto ordercustomerDto = new  OrderCustomerDto();
		if (!orderList.isEmpty()) {
			Item item = null;
			try {

				for (Order order : orderList) {
					ordercustomerDto.setName(order.getCustomerName());
					ordercustomerDto.setBillingaddress(order.getBillingAddressId());
					ordercustomerDto.setShippingAddress(order.getShippingAddressId());
					ordercustomerDto.setPhoneNumber(order.getCustomerPhoneNumber());

					////customer.add(ordercustomerDto);

				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return ordercustomerDto;
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
