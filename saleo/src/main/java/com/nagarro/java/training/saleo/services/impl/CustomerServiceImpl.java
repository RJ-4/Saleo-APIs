package com.nagarro.java.training.saleo.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.saleo.dao.CustomerDAO;
import com.nagarro.java.training.saleo.exceptions.CustomerNotFoundException;
import com.nagarro.java.training.saleo.models.Customer;
import com.nagarro.java.training.saleo.services.CustomerService;
import static com.nagarro.java.training.saleo.constants.Constants.*;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getAllCustomers() {

		return customerDAO.getAllCustomers();
	}

	@Override
	@Transactional
	public Customer getSingleCustomer(int customerId) {

		try {
			
			return customerDAO.getCustomer(customerId);
		
		} catch (Exception e) {

			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
		}
		
	}

	@Override
	@Transactional
	public Customer addNewCustomer(Customer newCustomer) {

		return customerDAO.saveCustomer(newCustomer);
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer updatedCustomer, int customerId) {

		Customer updatedExistingCustomer;
		
		try{
		
			updatedExistingCustomer = customerDAO.updateCustomer(updatedCustomer, customerId);
		
			updatedExistingCustomer.setCustomerId(customerId);
			
			return updatedExistingCustomer;
		
		} catch (NullPointerException e) {
			
			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
		}
		
	}
}
