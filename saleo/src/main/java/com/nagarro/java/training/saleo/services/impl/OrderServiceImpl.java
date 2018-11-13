package com.nagarro.java.training.saleo.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
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
	public Order addNewOrder(Order newOrder, int employeeId, int customerId, int productCode) {

		newOrder.setOrderDate(LocalDate.now());
		
		newOrder.setOrderTime(LocalTime.now());
		
		Order newAddedOrder = orderDAO.addNewOrder(newOrder, employeeId, customerId, productCode);
		
		productService.updateProductStock(newOrder, productCode);

		employeeService.updateEmployeeCashDrawer(newOrder, employeeId);
		
		return newAddedOrder;
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
}
