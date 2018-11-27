package com.nagarro.java.training.saleo.dao.impl;

import static com.nagarro.java.training.saleo.constants.Constants.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
		
		Employee currentEmployee = session.get(Employee.class, employeeId);
		
		Order currentOrder = session.get(Order.class, orderId);
		
		currentOrder.setEmployee(currentEmployee);
		
		currentOrder.setOrderDate(LocalDate.now());
		
		currentOrder.setOrderTime(LocalTime.now());
		
		currentOrder.setOrderStatus(updatedOrder.getOrderStatus());
		
		currentOrder.setProductQuantity(updatedOrder.getProductQuantity());
		
		currentOrder.setProductCost(currentOrder.getProductCost());
		
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

	@Override
	public List<Order> getOrdersInCustomersCart(String customerId) {

		Session session = factory.getCurrentSession();
		
		Customer currentCustomer = session.get(Customer.class, customerId);
		
		String getOrdersInCustomersCartQuery = GET_ORDERS_IN_CUSTOMERS_CART_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getOrdersInCustomersCartQuery);
		
		query.setParameter(CUSTOMER_PARAM, currentCustomer);
		
		@SuppressWarnings("unchecked")
		List<Order> ordersInCart = query.getResultList();
		
		return ordersInCart;
		
	}

	@Override
	public void deleteProductFromCart(String customerId, int orderId) {

		Session session = factory.getCurrentSession();
		
		Customer currentCustomer = session.get(Customer.class, customerId);
		
		String deleteProductFromCartQuery = DELETE_PRODUCT_FROM_CART_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(deleteProductFromCartQuery);
		
		query.setParameter(CUSTOMER_PARAM, currentCustomer);
		
		query.setParameter(ORDER_ID_PARAM, orderId);
		
		query.executeUpdate();
	}

	@Override
	public List<Order> updateProductQuantityInCartAndProceedToCheckout(String customerId,
																	List<Order> updatedOrders) {

		List<Order> checkedOutOrders = new ArrayList<Order>();
		
		Session session = factory.getCurrentSession();
		
		for(Order updatedOrder: updatedOrders) {
		
			Order currentOrder = session.get(Order.class, updatedOrder.getOrderId());
			
			currentOrder.setProductQuantity(updatedOrder.getProductQuantity());
			
			currentOrder.setProductCost(updatedOrder.getProductCost());
			
			checkedOutOrders.add(currentOrder);
		
		}
		
		return checkedOutOrders;
		
	}

	@Override
	public void deleteSavedForLaterOrder(int orderId) {

		Session session = factory.getCurrentSession();
		
		String deleteSavedForLaterOrderQuery = DELETE_FOR_SAVED_LATER_ORDER_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(deleteSavedForLaterOrderQuery);
		
		query.setParameter(ORDER_ID_PARAM, orderId);
		
		query.executeUpdate();
	}

	@Override
	public List<Order> getCashOrdersForLoggedInEmployee(int employeeId) {
		
		Session session = factory.getCurrentSession();
		
		Employee currentEmployee = session.get(Employee.class, employeeId);
		
		String getCashOrdersForSelectedEmployee = GET_CASH_ORDERS_FOR_LOGGED_IN_EMPLOYEE;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getCashOrdersForSelectedEmployee);
		
		query.setParameter(EMPLOYEE_PARAM, currentEmployee);
		
		query.setParameter(ORDER_DATE_PARAM, LocalDate.now());
		
		@SuppressWarnings("unchecked")
		List<Order> cashOrders = query.getResultList();
		
		return cashOrders;
	}
}
