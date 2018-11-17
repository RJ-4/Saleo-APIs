package com.nagarro.java.training.saleo.dao;

import java.util.List;

import com.nagarro.java.training.saleo.models.Employee;

public interface EmployeeDAO {

	public List<Employee> getEmployees();
	
	public Employee getEmployeeById(int employeeId) throws Exception;
	
	public void updateCashDrawer(double productPrice, int employeeId);
	
	public Employee addNewEmployee(Employee newEmployee);
	
	public Employee updateEmployeeAndAddAuthToken(Employee recentlyAddedEmployee);
	
	public void setCashDrawerAmountAtLogin(int currentEmployeeId, double startingAmountAtLogin);
}
