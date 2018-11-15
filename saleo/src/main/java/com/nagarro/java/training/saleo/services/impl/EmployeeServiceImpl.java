package com.nagarro.java.training.saleo.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.saleo.dao.EmployeeDAO;
import com.nagarro.java.training.saleo.exceptions.EmployeeNotFoundException;
import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.services.EmployeeService;
import com.nagarro.java.training.saleo.token.AuthToken;
import static com.nagarro.java.training.saleo.constants.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	AuthToken auth;
	
	@Override
	@Transactional
	public List<Employee> getEmployees(String authToken) {
	
		auth.checkUserAuthorization(authToken);
		
		return employeeDAO.getEmployees();
	}

	@Override
	@Transactional
	public Employee getCurrentEmployee(String authToken, int employeeId) {

		auth.checkUserAuthorization(authToken);
		
		try {
			
			return employeeDAO.getEmployeeById(employeeId);
		
		} catch (Exception e) {

			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE);
		}
	}

	@Override
	@Transactional
	public void updateEmployeeCashDrawer(Order newOrder, int employeeId) {
		
		double productPrice = newOrder.getProductCost();
		
		employeeDAO.updateCashDrawer(productPrice, employeeId);
	}

	@Override
	@Transactional
	public Employee registerEmployee(Employee newEmployee) {
		
		newEmployee = employeeDAO.addNewEmployee(newEmployee);
		
		newEmployee = employeeDAO.updateEmployeeAndAddAuthToken(newEmployee);
		
		return newEmployee;
	
	}

	@Override
	@Transactional
	public Employee authenticateEmployeeLogin(Employee currentEmployee) {
	
		
		Employee existingEmployee;
		
		try {
			
			existingEmployee = employeeDAO.getEmployeeById(currentEmployee.getEmployeeId());
		
		} catch (Exception e) {

			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE);
		}
		
		if(!currentEmployee.getEmployeePassword().equals(existingEmployee.getEmployeePassword())) {
			
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE);
		}
		
		return existingEmployee;
	}
}
