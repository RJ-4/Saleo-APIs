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
	
	public static final String GET_ALL_PRODUCTS_QUERY = "FROM Product";
	
	public static final String GET_CURRENT_EMPLOYEE_ORDERS_QUERY = "FROM Order WHERE employee = :employee "
																	+ "AND orderStatus != null "
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
	
	public static final int TAX_ON_PRODUCT = 10;
	
	public static final String ORDER_STATUS_COMPLETED = "Completed";
	
	public static final String ORDER_STATUS_SAVED_FOR_LATER = "Saved for Later";
	
	public static final String PRODUCT_SEARCH_PROPERTY = "productProperty";
	
	public static final String GET_SINGLE_PRODUCT_QUERY = "FROM Product WHERE "
															+ "productCode LIKE :" + PRODUCT_SEARCH_PROPERTY
															+ " OR productName LIKE :" + PRODUCT_SEARCH_PROPERTY
															+ " OR productDescription LIKE :" + PRODUCT_SEARCH_PROPERTY;

	public static final String GET_LAST_GENERATED_PRODUCT_CODE_QUERY = "SELECT MAX(productCode)"
																			+ " FROM Product";
	
	public static final String CUSTOMER_SEARCH_PROPERTY = "customerProperty";
	
	public static final String SEARCH_CUSTOMER_QUERY = "FROM Customer WHERE "
														+ "customerId LIKE :" + CUSTOMER_SEARCH_PROPERTY
														+ " OR customerName LIKE :" + CUSTOMER_SEARCH_PROPERTY
														+ " OR customerMobileNo LIKE :" + CUSTOMER_SEARCH_PROPERTY				
														+ " OR customerEmailId LIKE :" + CUSTOMER_SEARCH_PROPERTY;

	public static final String GET_LAST_GENERATED_CUSTOMER_ID_QUERY = "SELECT MAX(customerId) "
																		+ "FROM Customer";
	
	public static final String EMPLOYEE_PARAM = "employee";
	
	public static final String GET_SELECTED_EMPLOYEES_LAST_ORDER_QUERY = "FROM Order WHERE orderId = "
																		+ "(SELECT MAX(orderId) FROM Order"
																		+ " WHERE " 
																		+ EMPLOYEE_PARAM
																		+ "= :employee AND orderStatus "
																		+ "IN ('Completed', 'Saved for later'))";

	public static final String GET_LOW_STOCK_PRODUCTS_QUERY = "FROM Product WHERE productStock < 10";
	
	public static final String ORDER_DATE_PARAM = "orderDate";
	
	public static final String GET_TOTAL_ORDERS_PLACED_TODAY_QUERY = "SELECT COUNT(orderId) FROM Order"
																	+ " WHERE orderDate = :" 
																	+ ORDER_DATE_PARAM +" AND"
																	+ " orderStatus IN ('Completed', 'Saved for Later') "
																	+ "AND " + "employee = :" 
																	+ EMPLOYEE_PARAM;
	
	public static final String CUSTOMER_PARAM = "customer";
	
	public static final String GET_ORDERS_IN_CUSTOMERS_CART_QUERY = "FROM Order WHERE customer = :" + CUSTOMER_PARAM
																	+ " AND orderStatus = null";
	
	public static final String ORDER_ID_PARAM = "orderId";
	
	public static final String DELETE_PRODUCT_FROM_CART_QUERY = "DELETE FROM Order WHERE customer = :"
																+ CUSTOMER_PARAM + " AND orderId = :"
																+ ORDER_ID_PARAM;
	
	public static final String PRODUCT_QUANTITY_PARAM = "productQuantity";
	
	public static final String CASH = "Cash";
	
	public static final String DELETE_FOR_SAVED_LATER_ORDER_QUERY = "DELETE FROM Order WHERE orderId = :"
																	+ ORDER_ID_PARAM + " AND " 
																	+ "orderStatus = 'Saved for Later'";
	
	public static final String GET_CASH_ORDERS_FOR_LOGGED_IN_EMPLOYEE = "FROM Order WHERE employee = :"
																		+ EMPLOYEE_PARAM + " AND "
																		+ "modeOfPayment = 'Cash' AND "
																		+ "orderDate = :" + ORDER_DATE_PARAM
																		+ " ORDER BY orderTime DESC";
}
