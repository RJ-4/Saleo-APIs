package com.nagarro.java.training.saleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.saleo.models.Customer;
import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.services.CustomerService;
import com.nagarro.java.training.saleo.services.OrderService;

import static com.nagarro.java.training.saleo.constants.Constants.*;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(@RequestHeader(TOKEN) String authToken) {
		
		return customerService.getAllCustomers(authToken);
		
	}
	
	@GetMapping("/customers/{customerProperty}")
	public List<Customer> searchCustomer(@RequestHeader(TOKEN) String authToken, 
										@PathVariable String customerProperty) {
		
		return customerService.searchCustomer(authToken, customerProperty);
	}
	
	@PostMapping("/customers")
	public Customer addNewCustomer(@RequestHeader(TOKEN) String authToken,
										@RequestBody Customer newCustomer) {
		
		return customerService.addNewCustomer(authToken, newCustomer);
	
	}
	
	@PutMapping("/customers/{customerId}")
	public Customer updateCustomer(@RequestHeader(TOKEN) String authToken, 
									@RequestBody Customer updatedCustomer, 
									@PathVariable String customerId) {
		
		return customerService.updateCustomer(authToken, updatedCustomer, customerId);
	}
	
	@GetMapping("/customers/{customerId}/cart")
	public List<Order> getOrdersInCustomersCart(@RequestHeader(TOKEN) String authToken,
													@PathVariable String customerId) {

		return orderService.getOrdersInCustomersCart(authToken, customerId);
	}
	
	
	@DeleteMapping("/customers/{customerId}/orders")
	public void emptyCustomerCart(@RequestHeader(TOKEN) String authToken, 
										 @PathVariable String customerId) {
		
		orderService.emptyCustomerCart(authToken, customerId);
	}
	
	@DeleteMapping("/customers/{customerId}/cart/orders/{orderId}")
	public void deleteProductFromCart(@RequestHeader(TOKEN) String authToken,
										@PathVariable String customerId, 
										@PathVariable int orderId) {
		
		orderService.deleteProductFromCart(authToken, customerId, orderId);
	}
	
	@PutMapping("/customers/{customerId}/cart/orders/checkout")
	public List<Order> updateProductQuantityInCartAndProceedToCheckout(@RequestHeader(TOKEN) String authToken,
																	@PathVariable String customerId,
																	@RequestBody List<Order> updatedOrders) {
		
		return orderService.updateProductQuantityInCartAndProceedToCheckout(authToken, customerId, 
																			 updatedOrders);
	}
}
