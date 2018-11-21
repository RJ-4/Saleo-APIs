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
import static com.nagarro.java.training.saleo.constants.Constants.*;

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
	public Order addNewOrderInCart(String authToken, int employeeId, String customerId, 
										String productCode) {

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
	public Order saveOrPlaceOrder(String authToken, Order updatedOrder, int employeeId, String customerId, 
									String productCode, int orderId) {
		
		auth.checkUserAuthorization(authToken);
		
		updatedOrder = orderDAO.saveOrPlaceOrder(updatedOrder, employeeId, customerId, productCode, orderId);
		
		if (updatedOrder.getOrderStatus().equalsIgnoreCase(ORDER_STATUS_COMPLETED)) {
		
			productService.updateProductStock(authToken, updatedOrder, productCode);
			
			if(updatedOrder.getModeOfPayment().equalsIgnoreCase(CASH)){
			
				employeeService.updateEmployeeCashDrawer(updatedOrder, employeeId);
			}
		}
		
		return updatedOrder;
	}

	@Override
	@Transactional
	public void emptyCustomerCart(String authToken, String customerId) {
		
		auth.checkUserAuthorization(authToken);
		
		orderDAO.deleteItemsInCustomerCart(customerId);
	}

	@Override
	@Transactional
	public Order getCurrentEmployeesLastOrder(String authToken, int employeeId) {
	
		auth.checkUserAuthorization(authToken);
		
		return orderDAO.getCurrentEmployeesLastOrder(employeeId);
	}

	@Override
	@Transactional
	public long getTotalOrdersPlacedTodayByCurrentEmployee(int employeeId, String authToken) {

		auth.checkUserAuthorization(authToken);
	
		return orderDAO.getTotalOrdersPlacedTodayByCurrentEmployee(employeeId);
	}

	@Override
	@Transactional
	public List<Order> getOrdersInCustomersCart(String authToken, String customerId) {

		auth.checkUserAuthorization(authToken);
		
		return orderDAO.getOrdersInCustomersCart(customerId);
	}

	@Override
	@Transactional
	public void deleteProductFromCart(String authToken, String customerId, int orderId) {

		auth.checkUserAuthorization(authToken);
		
		orderDAO.deleteProductFromCart(customerId, orderId);
	}

	@Override
	@Transactional
	public List<Order> updateProductQuantityInCartAndProceedToCheckout(String authToken, String customerId,  
																	List<Order> updatedOrders) {
	
		auth.checkUserAuthorization(authToken);
		
		for(Order updatedOrder: updatedOrders) {
			
			updatedOrder.setProductCost(updatedOrder.getProductCost() * updatedOrder.getProductQuantity());
		
		}
		
		return orderDAO.updateProductQuantityInCartAndProceedToCheckout(customerId, 
																		updatedOrders);
	}
}
