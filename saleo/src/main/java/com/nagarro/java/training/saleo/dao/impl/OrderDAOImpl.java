package com.nagarro.java.training.saleo.dao.impl;

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
	public Order addNewOrder(Order newOrder, int employeeId, int customerId, int productCode) {

		Session session = factory.getCurrentSession();
		
		Customer currentCustomer = session.get(Customer.class, customerId);
		currentCustomer.addOrder(newOrder);
		
		Employee currentEmployee = session.get(Employee.class, employeeId);
		currentEmployee.addOrder(newOrder);
		
		Product selectedProduct = session.get(Product.class, productCode);
		selectedProduct.addOrder(newOrder);
		
		Double productCost = selectedProduct.getProductUnitPrice() * newOrder.getProductQuantity();
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
}
