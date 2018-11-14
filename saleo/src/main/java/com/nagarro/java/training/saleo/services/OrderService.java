package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Order;

public interface OrderService {

	public Order addNewOrderInCart(int employeeId, int customerId, int productCode);
	
	public List<Order> getCurrentEmployeeOrders(int employeeId);
	
	public Order getCurrentEmployeeSelectedOrder(int employeeId, int orderId);
	
	public Order saveOrPlaceOrder(Order updatedOrder, int employeeId, int customerId, int productCode,
									int orderId);
	
	public void emptyCustomerCart(int customerId);
}
