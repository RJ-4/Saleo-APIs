package com.nagarro.java.training.saleo.dao;

import com.nagarro.java.training.saleo.models.Customer;

import java.util.List;

public interface CustomerDAO {

	public List<Customer> getAllCustomers();
	
	public Customer getCustomer(int customerId) throws Exception;
	
	public Customer saveCustomer(Customer newCustomer);
	
	public Customer updateCustomer(Customer updatedCustomer, int customerId);
	
}
