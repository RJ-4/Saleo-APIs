package com.nagarro.java.training.saleo.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.java.training.saleo.dao.OrderDAO;
import com.nagarro.java.training.saleo.models.Customer;
import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.models.Product;
import static com.nagarro.java.training.saleo.constants.Constants.*;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	SessionFactory factory;
	
	@Override
	public Order addNewOrderInCart(Order newOrder, int employeeId, String customerId, String productCode) {

		Session session = factory.getCurrentSession();
		
		Customer currentCustomer = session.get(Customer.class, customerId);
		currentCustomer.addOrder(newOrder);
		
		Employee currentEmployee = session.get(Employee.class, employeeId);
		currentEmployee.addOrder(newOrder);
		
		Product selectedProduct = session.get(Product.class, productCode);
		selectedProduct.addOrder(newOrder);
		
		Double productCost = selectedProduct.getProductUnitPrice();
		newOrder.setProductCost(productCost);
		
		session.save(newOrder);
		
		return newOrder;
		
	}
	
	public List<Order> getCurrentEmployeeOrders(int employeeId) {
		
		Session session = factory.getCurrentSession();
		
		Employee currentEmployee = session.get(Employee.class, employeeId);
		
		String getCurrentEmployeeOrdersQuery = GET_CURRENT_EMPLOYEE_ORDERS_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getCurrentEmployeeOrdersQuery);
		
		query.setParameter("employee", currentEmployee);
		
		@SuppressWarnings("unchecked")
		List<Order> fetchedOrders = query.getResultList();
		
		return fetchedOrders;
		
	}

	@Override
	public Order getCurrentEmployeeSelectedOrder(int employeeId, int orderId) {

		Session session = factory.getCurrentSession();
		
		Employee currentEmployee = session.get(Employee.class, employeeId);
		
		String getCurrentEmployeeSelectedOrderQuery = GET_CURRENT_EMPLOYEE_SELECTED_ORDER_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getCurrentEmployeeSelectedOrderQuery);
		
		query.setParameter("employee", currentEmployee);
		
		query.setParameter("orderId", orderId);
		
		Order selectedOrder = (Order) query.getSingleResult();
		
		return selectedOrder;
		
	}

	@Override
	public Order saveOrPlaceOrder(Order updatedOrder, int employeeId, String customerId,
									String productCode, int orderId) {
		
		Session session = factory.getCurrentSession();
		
		Order currentOrder = session.get(Order.class, orderId);
		
		currentOrder.setOrderDate(LocalDate.now());
		
		currentOrder.setOrderTime(LocalTime.now());
		
		currentOrder.setOrderStatus(updatedOrder.getOrderStatus());
		
		currentOrder.setProductQuantity(updatedOrder.getProductQuantity());
		
		currentOrder.setProductCost(currentOrder.getProductCost() * updatedOrder.getProductQuantity());
		
		if(updatedOrder.getOrderStatus().equalsIgnoreCase("Completed")) {
			
			currentOrder.setModeOfPayment(updatedOrder.getModeOfPayment());
		}
		
		return currentOrder;
	}

	@Override
	public void deleteItemsInCustomerCart(String customerId) {

		Session session = factory.getCurrentSession();
		
		Customer currentCustomer = session.get(Customer.class, customerId);
		
		String deleteProductsInCartQuery = DELETE_PRODUCTS_IN_CART_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(deleteProductsInCartQuery);
		
		query.setParameter("customer", currentCustomer);
		
		query.executeUpdate();
	}

	@Override
	public Order getCurrentEmployeesLastOrder(int employeeId) {
		
		Session session = factory.getCurrentSession();
		
		Employee currentEmployee = session.get(Employee.class, employeeId);
		
		String getCurrentEmployeesLastOrderQuery = GET_SELECTED_EMPLOYEES_LAST_ORDER_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getCurrentEmployeesLastOrderQuery);
		
		query.setParameter(EMPLOYEE_PARAM, currentEmployee);
		
		Order lastOrderOfCurrentEmployee = (Order) query.getSingleResult();
		
		return lastOrderOfCurrentEmployee;
	}

	@Override
	public long getTotalOrdersPlacedTodayByCurrentEmployee(int employeeId) {
		
		Session session = factory.getCurrentSession();

		Employee currentEmployee = session.get(Employee.class, employeeId);
		
		String getTotalOrdersPlacedTodayQuery = GET_TOTAL_ORDERS_PLACED_TODAY_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getTotalOrdersPlacedTodayQuery);
		
		query.setParameter(EMPLOYEE_PARAM, currentEmployee);
		
		query.setParameter(ORDER_DATE_PARAM, LocalDate.now());
		
		long totalOrdersPlacedToday = (Long) query.getSingleResult();
		
		return totalOrdersPlacedToday;
	}
}
