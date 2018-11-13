package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Order;

public interface OrderService {

	public Order addNewOrder(Order newOrder, int employeeId, int customerId, int productCode);
	
	public List<Order> getCurrentEmployeeOrders(int employeeId);
	
	public Order getCurrentEmployeeSelectedOrder(int employeeId, int orderId);
	
}
