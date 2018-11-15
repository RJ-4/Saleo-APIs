package com.nagarro.java.training.saleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.services.EmployeeService;
import com.nagarro.java.training.saleo.services.OrderService;
import static com.nagarro.java.training.saleo.constants.Constants.*;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees(@RequestHeader(TOKEN) String authToken) {
		
		return employeeService.getEmployees(authToken);
	}
	
	
	@PostMapping("/employees")
	public Employee registerEmployee(@RequestBody Employee newEmployee) {
		
		return employeeService.registerEmployee(newEmployee);
	}
	
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeById(@RequestHeader(TOKEN) String authToken, @PathVariable Integer employeeId) {
		
		return employeeService.getCurrentEmployee(authToken, employeeId);
	}
	
	
	@PostMapping("/employees/login")
	public Employee authenticateEmployeeLogin(@RequestBody Employee currentEmployee) {
		
		return employeeService.authenticateEmployeeLogin(currentEmployee);
	}
	
	
	@PostMapping("employees/{employeeId}/customers/{customerId}/products/{productCode}/orders")
	public Order addOrderInCart(@RequestHeader(TOKEN) String authToken, @PathVariable int employeeId, 
									@PathVariable int customerId, @PathVariable int productCode) {
		
		return orderService.addNewOrderInCart(authToken, employeeId, customerId, productCode);
	}
	
	
	@PutMapping("employees/{employeeId}/customers/{customerId}/products/{productCode}/orders/{orderId}")
	public Order saveOrPlaceOrder(@RequestHeader(TOKEN) String authToken, @RequestBody Order updatedOrder, @PathVariable int employeeId, 
										@PathVariable int customerId, @PathVariable int productCode, 
										@PathVariable int orderId){
		
		return orderService.saveOrPlaceOrder(authToken, updatedOrder, employeeId, customerId, productCode, orderId);
		
	}
	
	
	@GetMapping("/employees/{employeeId}/orders")
	public List<Order> getCurrentEmployeeOrders(@RequestHeader(TOKEN) String authToken, 
													@PathVariable int employeeId) {
		
		return orderService.getCurrentEmployeeOrders(authToken, employeeId);
	}
	
	
	@GetMapping("/employees/{employeeId}/orders/{orderId}")
	public Order getCurrentEmployeeSelectedOrder(@RequestHeader(TOKEN) String authToken, 
													@PathVariable int employeeId, @PathVariable int orderId) {
		
		return orderService.getCurrentEmployeeSelectedOrder(authToken, employeeId, orderId);
	}
	
	@DeleteMapping("/employees/{employeeId}/customers/{customerId}/orders")
	public void emptyCustomerCart(@RequestHeader(TOKEN) String authToken, 
										@PathVariable int employeeId, @PathVariable int customerId) {
		
		orderService.emptyCustomerCart(authToken, customerId);
	}
}
