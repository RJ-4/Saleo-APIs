package com.nagarro.java.training.saleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
	public Employee getEmployeeById(@RequestHeader(TOKEN) String authToken, 
										@PathVariable Integer employeeId) {
		
		return employeeService.getCurrentEmployee(authToken, employeeId);
	}
	
	
	@PostMapping("/employees/login")
	public Employee authenticateEmployeeLogin(@RequestBody Employee currentEmployee) {
		
		return employeeService.authenticateEmployeeLogin(currentEmployee);
	}
	
	@PutMapping("/employees/{employeeId}/profile/update")
	public Employee updateEmployee(@RequestHeader(TOKEN) String authToken,
									@PathVariable int employeeId,
									@RequestBody Employee updatedEmployee) {
		
		return employeeService.updateEmployee(authToken, employeeId, updatedEmployee);
	}
	
	
	@PostMapping("employees/{employeeId}/customers/{customerId}/products/{productCode}/orders")
	public Order addOrderInCart(@RequestHeader(TOKEN) String authToken, @PathVariable int employeeId, 
									@PathVariable String customerId, @PathVariable String productCode) {
		
		return orderService.addNewOrderInCart(authToken, employeeId, customerId, productCode);
	}
	
	
	@PutMapping("employees/{employeeId}/customers/{customerId}/products/{productCode}/orders/{orderId}")
	public Order saveOrPlaceOrder(@RequestHeader(TOKEN) String authToken, 
									@RequestBody Order updatedOrder, 
									@PathVariable int employeeId, 
									@PathVariable String customerId, 
									@PathVariable String productCode, 
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

	
	@GetMapping("/employees/{employeeId}/orders/last-order")
	public Order getCurrentEmployeesLastOrder(@RequestHeader(TOKEN) String authToken, 
												@PathVariable int employeeId) {
		
		return orderService.getCurrentEmployeesLastOrder(authToken, employeeId);
	}
	
	@GetMapping("/employees/{employeeId}/total-orders-today")
	public long getTotalOrdersPlacedTodayByCurrentEmployee(@PathVariable int employeeId, 
															@RequestHeader(TOKEN) String authToken) {
		
		return orderService.getTotalOrdersPlacedTodayByCurrentEmployee(employeeId, authToken);
	}
}
