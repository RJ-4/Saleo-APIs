package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();
	
	public Customer getSingleCustomer(int customerId);
	
	public Customer addNewCustomer(Customer newCustomer);
	
	public Customer updateCustomer(Customer updatedCustomer, int customerId);
}
