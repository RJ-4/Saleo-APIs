package com.nagarro.java.training.saleo.dao;

import java.util.List;

import com.nagarro.java.training.saleo.models.Employee;

public interface EmployeeDAO {

	public List<Employee> getEmployees();
	
	public Employee getEmployeeById(int employeeId);
}
