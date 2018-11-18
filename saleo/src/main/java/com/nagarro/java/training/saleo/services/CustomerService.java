package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers(String authToken);
	
	public List<Customer> searchCustomer(String authToken, String cutomerProperty);
	
	public Customer addNewCustomer(String authToken, Customer newCustomer);
	
	public Customer updateCustomer(String authToken, Customer updatedCustomer, String customerId);
	
	public String generateCustomerIdForNewCustomer();
}
