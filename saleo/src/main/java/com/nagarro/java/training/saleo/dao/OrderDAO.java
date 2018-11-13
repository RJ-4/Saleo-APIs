package com.nagarro.java.training.saleo.dao;

import java.util.List;

import com.nagarro.java.training.saleo.models.Order;

public interface OrderDAO {

	public Order addNewOrder(Order newOrder, int employeeId, int customerId, int productCode);

	public List<Order> getCurrentEmployeeOrders(int employeeId);
	
	public Order getCurrentEmployeeSelectedOrder(int employeeId, int orderId);
}
