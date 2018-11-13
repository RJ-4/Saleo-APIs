package com.nagarro.java.training.saleo.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.saleo.dao.EmployeeDAO;
import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	@Override
	@Transactional
	public List<Employee> getEmployees() {
	
		return employeeDAO.getEmployees();
	}

	@Override
	@Transactional
	public Employee getCurrentEmployee(int employeeId) {

		return employeeDAO.getEmployeeById(employeeId);
	}

	@Override
	@Transactional
	public void updateEmployeeCashDrawer(Order newOrder, int employeeId) {
		
		double productPrice = newOrder.getProductCost();
		
		employeeDAO.updateCashDrawer(productPrice, employeeId);
	}
	
	

}
