package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.models.Order;

public interface EmployeeService {

	public List<Employee> getEmployees();
	
	public Employee getCurrentEmployee(int employeeId);
	
	public void updateEmployeeCashDrawer(Order newOrder, int employeeId);
}
