package com.nagarro.java.training.saleo.constants;

public class Constants {

	public static final String BASE_PACKAGE_FOR_SCAN = "com.nagarro.java.training.saleo";
	
	public static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
	
	public static final String VIEW_RESOLVER_SUFFIX = ".jsp";
	
	public static final String RESOURCE_HANDLER = "/resources/**";
	
	public static final String RESOURCE_LOCATION = "/resources/";
	
	public static final String COMPONENT_SCAN = "{" + BASE_PACKAGE_FOR_SCAN + "}";
	
	public static final String HIBERNATE_CONFIG_PROPERTIES_LOCATION = "classpath:application.properties";
	
	public static final String GET_ALL_EMPLOYEES_QUERY = "FROM Employee";
	
	public static final String GET_CURRENT_EMPLOYEE_QUERY = "FROM Employee WHERE employeeId = :employeeId";
	
	public static final String GET_ALL_CUSTOMERS_QUERY = "FROM Customer";
	
	public static final String GET_SINGLE_CUSTOMER_QUERY = "FROM Customer WHERE customerId = :customerId";
	
	public static final String GET_ALL_PRODUCTS_QUERY = "FROM Product";
	
	public static final String GET_SINGLE_PRODUCT_QUERY = "FROM Product WHERE productId = :productId";
	
	public static final String GET_CURRENT_EMPLOYEE_ORDERS_QUERY = "FROM Order WHERE employee = :employee "
																	+ "ORDER BY orderDate DESC, "
																	+ "orderTime DESC";

	public static final String GET_CURRENT_EMPLOYEE_SELECTED_ORDER_QUERY = "FROM Order WHERE "
																			+ "employee = :employee AND "
																			+ "orderId = :orderId";
	
	public static final String SLUG = "saleo1234567890";
	
	public static final String EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE = "Invalid employee ID or password!!!";
	
	public static final String CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE = "No such customer exists!!!";
			
	public static final String PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE = "No such product exists!!!";
			
	public static final String ORDER_NOT_FOUND_EXCEPTION_MESSAGE = "No such order exists!!!";
	
	public static final String DELETE_PRODUCTS_IN_CART_QUERY = "DELETE FROM Order WHERE customer = : customer "
																+ "AND modeOfPayment = null";
	
	public static final String TOKEN = "Token";
	
	public static final String USER_NOT_AUTHORIZED_EXCEPTION_MESSAGE = "Not authorized!!!";
}
