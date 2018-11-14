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

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ProductService productService;
	
	@Override
	@Transactional
	public Order addNewOrderInCart(int employeeId, int customerId, int productCode) {

		Order newOrder = new Order();
		
		newOrder.setProductQuantity(1);
		
		newOrder = orderDAO.addNewOrderInCart(newOrder, employeeId, customerId, productCode);
		
		return newOrder;
	}

	@Override
	@Transactional
	public List<Order> getCurrentEmployeeOrders(int employeeId) {

		return orderDAO.getCurrentEmployeeOrders(employeeId);
	}

	@Override
	@Transactional
	public Order getCurrentEmployeeSelectedOrder(int employeeId, int orderId) {

		return orderDAO.getCurrentEmployeeSelectedOrder(employeeId, orderId);
	}

	@Override
	@Transactional
	public Order saveOrPlaceOrder(Order updatedOrder, int employeeId, int customerId, int productCode,
									int orderId) {
		
		updatedOrder = orderDAO.saveOrPlaceOrder(updatedOrder, employeeId, customerId, productCode, orderId);
		
		if (updatedOrder.getOrderStatus().equalsIgnoreCase("Completed")) {
		
			productService.updateProductStock(updatedOrder, productCode);
			
			employeeService.updateEmployeeCashDrawer(updatedOrder, employeeId);
			
		}
		
		return updatedOrder;
	}

	@Override
	@Transactional
	public void emptyCustomerCart(int customerId) {
		
		orderDAO.deleteItemsInCustomerCart(customerId);
	}
}
