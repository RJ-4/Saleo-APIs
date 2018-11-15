package com.nagarro.java.training.saleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.saleo.models.Customer;
import com.nagarro.java.training.saleo.services.CustomerService;
import static com.nagarro.java.training.saleo.constants.Constants.*;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(@RequestHeader(TOKEN) String authToken) {
		
		return customerService.getAllCustomers(authToken);
		
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getSingleCustomer(@RequestHeader(TOKEN) String authToken, @PathVariable int customerId) {
		
		return customerService.getSingleCustomer(authToken, customerId);
	}
	
	@PostMapping("/customers")
	public Customer addNewCustomer(@RequestHeader(TOKEN) String authToken, @RequestBody Customer newCustomer) {
		
		return customerService.addNewCustomer(authToken, newCustomer);
	
	}
	
	@PutMapping("/customers/{customerId}")
	public Customer updateCustomer(@RequestHeader(TOKEN) String authToken, 
									@RequestBody Customer updatedCustomer, @PathVariable int customerId) {
		
		return customerService.updateCustomer(authToken, updatedCustomer, customerId);
	}
}
