package com.nagarro.java.training.saleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.services.EmployeeService;
import com.nagarro.java.training.saleo.services.OrderService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		
		return employeeService.getEmployees();
	}
	
	
	@PostMapping("/employees")
	public Employee registerEmployee(@RequestBody Employee newEmployee) {
		
		return employeeService.registerEmployee(newEmployee);
	}
	
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeById(@PathVariable Integer employeeId) {
		
		return employeeService.getCurrentEmployee(employeeId);
	}
	
	
	@PostMapping("/employees/login")
	public Employee authenticateEmployeeLogin(@RequestBody Employee currentEmployee) {
		
		return employeeService.authenticateEmployeeLogin(currentEmployee);
	}
	
	
	@PostMapping("employees/{employeeId}/customers/{customerId}/products/{productCode}/orders")
	public Order addOrderInCart(@PathVariable int employeeId, 
							@PathVariable int customerId, @PathVariable int productCode) {
		
		return orderService.addNewOrderInCart(employeeId, customerId, productCode);
	}
	
	
	@PutMapping("employees/{employeeId}/customers/{customerId}/products/{productCode}/orders/{orderId}")
	public Order saveOrPlaceOrder(@RequestBody Order updatedOrder, @PathVariable int employeeId, 
			@PathVariable int customerId, @PathVariable int productCode, @PathVariable int orderId){
		
		return orderService.saveOrPlaceOrder(updatedOrder, employeeId, customerId, productCode, orderId);
		
	}
	
	
	@GetMapping("/employees/{employeeId}/orders")
	public List<Order> getCurrentEmployeeOrders(@PathVariable int employeeId) {
		
		return orderService.getCurrentEmployeeOrders(employeeId);
	}
	
	
	@GetMapping("/employees/{employeeId}/orders/{orderId}")
	public Order getCurrentEmployeeSelectedOrder(@PathVariable int employeeId, @PathVariable int orderId) {
		
		return orderService.getCurrentEmployeeSelectedOrder(employeeId, orderId);
	}
	
	@DeleteMapping("/employees/{employeeId}/customers/{customerId}/orders")
	public void emptyCustomerCart(@PathVariable int employeeId, @PathVariable int customerId) {
		
		orderService.emptyCustomerCart(customerId);
	}
}
