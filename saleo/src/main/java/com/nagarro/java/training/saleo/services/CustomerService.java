package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers(String authToken);
	
	public Customer getSingleCustomer(String authToken, int customerId);
	
	public Customer addNewCustomer(String authToken, Customer newCustomer);
	
	public Customer updateCustomer(String authToken, Customer updatedCustomer, int customerId);
}
