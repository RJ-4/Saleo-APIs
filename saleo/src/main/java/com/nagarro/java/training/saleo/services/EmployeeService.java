package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.models.Order;

public interface EmployeeService {

	public List<Employee> getEmployees(String authToken);
	
	public Employee getCurrentEmployee(String authToken, int employeeId);
	
	public void updateEmployeeCashDrawer(Order newOrder, int employeeId);
	
	public Employee registerEmployee(Employee newEmployee);
	
	public Employee authenticateEmployeeLogin(Employee currentEmployee);
	
	public void setCashDrawerAtLogin(int currentEmployeeId, double startingAmountAtLogin);
	
	public Employee updateEmployee(String authToken, int employeeId, Employee updatedEmployee);
}
