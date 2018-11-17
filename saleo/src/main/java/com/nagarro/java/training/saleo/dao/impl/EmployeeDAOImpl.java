package com.nagarro.java.training.saleo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.java.training.saleo.dao.EmployeeDAO;
import com.nagarro.java.training.saleo.models.Employee;
import com.nagarro.java.training.saleo.token.AuthToken;

import static com.nagarro.java.training.saleo.constants.Constants.*;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	SessionFactory factory;
	
	@Autowired
	AuthToken authToken;
	
	@Override
	public List<Employee> getEmployees() {

		Session session = factory.getCurrentSession();
		
		String getEmployeesQuery = GET_ALL_EMPLOYEES_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getEmployeesQuery);
		
		@SuppressWarnings("unchecked")
		List<Employee> employeeList = query.getResultList();
		
		return employeeList;
	
	}

	@Override
	public Employee getEmployeeById(int employeeId) throws Exception {
		
		Session session = factory.getCurrentSession();
		
		String getEmployeeByIdQuery = GET_CURRENT_EMPLOYEE_QUERY;

		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getEmployeeByIdQuery);

		query.setParameter("employeeId", employeeId);
		
		Employee currentEmployee = (Employee) query.getSingleResult();
		
		return currentEmployee;
	}

	@Override
	public void updateCashDrawer(double productPrice, int employeeId) {

		Session session = factory.getCurrentSession();
		
		Employee currentEmployee = session.get(Employee.class, employeeId);

		double currentCashDrawer = currentEmployee.getEmployeeCashDrawer();
		
		currentEmployee.setEmployeeCashDrawer(currentCashDrawer + productPrice + TAX_ON_PRODUCT);
	}

	@Override
	public Employee addNewEmployee(Employee newEmployee) {
		
		Session session = factory.getCurrentSession();
		
		if(newEmployee.getEmployeeCashDrawer() == null) {
			
			newEmployee.setEmployeeCashDrawer(0.0);
		}
		
		session.save(newEmployee);
		
		return newEmployee;
	}

	@Override
	public Employee updateEmployeeAndAddAuthToken(Employee recentlyAddedEmployee) {

		Session session = factory.getCurrentSession();
		
		Employee currentEmployee = session.get(Employee.class, recentlyAddedEmployee.getEmployeeId());
		
		String token = authToken.generateToken(recentlyAddedEmployee.getEmployeeId());
		
		currentEmployee.setToken(token);
		
		return currentEmployee;
	}

	@Override
	public void setCashDrawerAmountAtLogin(int currentEmployeeId, double startingAmountAtLogin) {
		
		Session session = factory.getCurrentSession();
		
		Employee currentEmployee = session.get(Employee.class, currentEmployeeId);
		
		currentEmployee.setEmployeeCashDrawer(startingAmountAtLogin);
	}

}
