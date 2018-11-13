package com.nagarro.java.training.saleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.services.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		
		return employeeService.getEmployees();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeById(@PathVariable Integer employeeId) {
		
		return employeeService.getCurrentEmployee(employeeId);
	}
	
	@RequestMapping("/employees/{employeeId}/orders")
	public OrdersController getOrdersController() {
		
		return new OrdersController();
	}
}
