package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Order;

public interface OrderService {

	public Order addNewOrderInCart(String authToken, int employeeId, String customerId, String productCode);
	
	public List<Order> getCurrentEmployeeOrders(String authToken, int employeeId);
	
	public Order getCurrentEmployeeSelectedOrder(String authToken, int employeeId, int orderId);
	
	public Order saveOrPlaceOrder(String authToken, Order updatedOrder, int employeeId, String customerId, 
									String productCode, int orderId);
	
	public void emptyCustomerCart(String authToken, String customerId);
	
	public Order getCurrentEmployeesLastOrder(String authToken, int employeeId);
}
