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
	
	public static final String UPDATE_PRODUCT_QUERY = "UPDATE Product SET productName = :productName, "
														+ "productDescription = :productDescription, "
														+ "productStock = :productStock, "
														+ "productUnitPrice = :productUnitPrice "
														+ "WHERE productCode = :productCode";
}
