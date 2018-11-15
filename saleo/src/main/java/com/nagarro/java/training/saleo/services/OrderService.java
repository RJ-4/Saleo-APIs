package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Order;

public interface OrderService {

	public Order addNewOrderInCart(String authToken, int employeeId, int customerId, int productCode);
	
	public List<Order> getCurrentEmployeeOrders(String authToken, int employeeId);
	
	public Order getCurrentEmployeeSelectedOrder(String authToken, int employeeId, int orderId);
	
	public Order saveOrPlaceOrder(String authToken, Order updatedOrder, int employeeId, int customerId, int productCode,
									int orderId);
	
	public void emptyCustomerCart(String authToken, int customerId);
}
