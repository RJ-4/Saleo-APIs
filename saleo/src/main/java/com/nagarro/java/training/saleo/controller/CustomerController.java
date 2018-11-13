package com.nagarro.java.training.saleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.saleo.models.Customer;
import com.nagarro.java.training.saleo.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		
		return customerService.getAllCustomers();
		
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getSingleCustomer(@PathVariable int customerId) {
		
		return customerService.getSingleCustomer(customerId);
	}
	
	@PostMapping("/customers")
	public Customer addNewCustomer(@RequestBody Customer newCustomer) {
		
		return customerService.addNewCustomer(newCustomer);
	
	}
	
	@PutMapping("/customers/{customerId}")
	public Customer updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable int customerId) {
		
		return customerService.updateCustomer(updatedCustomer, customerId);
	}
}
