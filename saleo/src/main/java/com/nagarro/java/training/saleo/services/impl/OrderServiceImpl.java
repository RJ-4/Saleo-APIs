package com.nagarro.java.training.saleo.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.saleo.dao.OrderDAO;
import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.services.EmployeeService;
import com.nagarro.java.training.saleo.services.OrderService;
import com.nagarro.java.training.saleo.services.ProductService;
import com.nagarro.java.training.saleo.token.AuthToken;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AuthToken auth;
	
	@Override
	@Transactional
	public Order addNewOrderInCart(String authToken, int employeeId, int customerId, int productCode) {

		auth.checkUserAuthorization(authToken);

		Order newOrder = new Order();
		
		newOrder.setProductQuantity(1);
		
		newOrder = orderDAO.addNewOrderInCart(newOrder, employeeId, customerId, productCode);
		
		return newOrder;
	}

	@Override
	@Transactional
	public List<Order> getCurrentEmployeeOrders(String authToken, int employeeId) {

		auth.checkUserAuthorization(authToken);
		
		return orderDAO.getCurrentEmployeeOrders(employeeId);
	}

	@Override
	@Transactional
	public Order getCurrentEmployeeSelectedOrder(String authToken, int employeeId, int orderId) {

		auth.checkUserAuthorization(authToken);
		
		return orderDAO.getCurrentEmployeeSelectedOrder(employeeId, orderId);
	}

	@Override
	@Transactional
	public Order saveOrPlaceOrder(String authToken, Order updatedOrder, int employeeId, int customerId, 
									int productCode, int orderId) {
		
		auth.checkUserAuthorization(authToken);
		
		updatedOrder = orderDAO.saveOrPlaceOrder(updatedOrder, employeeId, customerId, productCode, orderId);
		
		if (updatedOrder.getOrderStatus().equalsIgnoreCase("Completed")) {
		
			productService.updateProductStock(authToken, updatedOrder, productCode);
			
			employeeService.updateEmployeeCashDrawer(updatedOrder, employeeId);
			
		}
		
		return updatedOrder;
	}

	@Override
	@Transactional
	public void emptyCustomerCart(String authToken, int customerId) {
		
		auth.checkUserAuthorization(authToken);
		
		orderDAO.deleteItemsInCustomerCart(customerId);
	}
}
