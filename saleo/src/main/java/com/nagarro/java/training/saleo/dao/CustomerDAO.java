package com.nagarro.java.training.saleo.dao;

import com.nagarro.java.training.saleo.models.Customer;

import java.util.List;

public interface CustomerDAO {

	public List<Customer> getAllCustomers();
	
	public  List<Customer> searchCustomer(String cutomerProperty) throws Exception;
	
	public Customer saveCustomer(Customer newCustomer);
	
	public Customer updateCustomer(Customer updatedCustomer, String customerId);
	
	public String getLatestCustomerId();
	
}
