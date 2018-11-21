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
	public List<Customer> searchCustomer(String authToken, String cutomerProperty) {

		auth.checkUserAuthorization(authToken); 
		
		List<Customer> searchedCustomer = null;
		
		try {
			
			searchedCustomer = customerDAO.searchCustomer(cutomerProperty);
			
			if(searchedCustomer.size() == 0) {

				throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
			
			} else {
				
				return searchedCustomer;
			}
		
		} catch (Exception e) {
			
			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
		}

		
	}

	@Override
	@Transactional
	public Customer addNewCustomer(String authToken, Customer newCustomer) {

		auth.checkUserAuthorization(authToken);
		
		String newCustomerId = generateCustomerIdForNewCustomer();
		
		newCustomer.setCustomerId(newCustomerId);
		
		return customerDAO.saveCustomer(newCustomer);
	}

	@Override
	@Transactional
	public Customer updateCustomer(String authToken, Customer updatedCustomer, String customerId) {

		auth.checkUserAuthorization(authToken);
		
		Customer updatedExistingCustomer;
		
		try{
		
			updatedExistingCustomer = customerDAO.updateCustomer(updatedCustomer, customerId);
		
//			updatedExistingCustomer.setCustomerId(customerId);
			
			return updatedExistingCustomer;
		
		} catch (NullPointerException e) {
			
			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
		}
		
	}

	@Override
	@Transactional
	public String generateCustomerIdForNewCustomer() {
	
		String latestCustomerId = customerDAO.getLatestCustomerId();
		
		int latestCustomerIdNumber = Integer.parseInt(latestCustomerId);
		
		String newCustomerId = "" + (latestCustomerIdNumber + 1);
		
		return newCustomerId;
	}
}
