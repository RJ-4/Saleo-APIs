package com.nagarro.java.training.saleo.services.impl;

import static com.nagarro.java.training.saleo.constants.Constants.CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.saleo.dao.CustomerDAO;
import com.nagarro.java.training.saleo.exceptions.CustomerNotFoundException;
import com.nagarro.java.training.saleo.models.Customer;
import com.nagarro.java.training.saleo.services.CustomerService;
import com.nagarro.java.training.saleo.token.AuthToken;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	AuthToken auth;
	
	@Override
	@Transactional
	public List<Customer> getAllCustomers(String authToken) {

		auth.checkUserAuthorization(authToken);
		
		return customerDAO.getAllCustomers();
	}

	@Override
	@Transactional
	public Customer getSingleCustomer(String authToken, int customerId) {

		auth.checkUserAuthorization(authToken); 

		try {
			
			return customerDAO.getCustomer(customerId);
		
		} catch (Exception e) {

			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
		}
	}

	@Override
	@Transactional
	public Customer addNewCustomer(String authToken, Customer newCustomer) {

		auth.checkUserAuthorization(authToken);
		
		return customerDAO.saveCustomer(newCustomer);
	}

	@Override
	@Transactional
	public Customer updateCustomer(String authToken, Customer updatedCustomer, int customerId) {

		auth.checkUserAuthorization(authToken);
		
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
