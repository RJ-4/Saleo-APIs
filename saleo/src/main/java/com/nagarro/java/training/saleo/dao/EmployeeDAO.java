package com.nagarro.java.training.saleo.dao;

import java.util.List;

import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.models.Order;

public interface EmployeeDAO {

	public List<Employee> getEmployees();
	
	public Employee getEmployeeById(int employeeId);
	
	public void updateCashDrawer(double productPrice, int employeeId);
}
