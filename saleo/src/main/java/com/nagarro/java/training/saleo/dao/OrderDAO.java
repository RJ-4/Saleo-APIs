package com.nagarro.java.training.saleo.dao;

import java.util.List;

import com.nagarro.java.training.saleo.models.Order;

public interface OrderDAO {

	public Order addNewOrderInCart(Order newOrder, int employeeId, String customerId, String productCode);

	public List<Order> getCurrentEmployeeOrders(int employeeId);
	
	public Order getCurrentEmployeeSelectedOrder(int employeeId, int orderId);
	
	public Order saveOrPlaceOrder(Order updatedOrder, int employeeId, String customerId, 
									String productCode,	int orderId);
	
	public void deleteItemsInCustomerCart(String customerId);
	
	public Order getCurrentEmployeesLastOrder(int employeeId);
	
	public long getTotalOrdersPlacedTodayByCurrentEmployee(int employeeId);
	
	public List<Order> getOrdersInCustomersCart(String customerId);
	
	public void deleteProductFromCart(String customerId, int orderId);
	
	public List<Order> updateProductQuantityInCartAndProceedToCheckout(String customerId,
																	List<Order> updatedOrders);
	
	public void deleteSavedForLaterOrder(int orderId);
	
	public List<Order> getCashOrdersForLoggedInEmployee(int employeeId);
}
