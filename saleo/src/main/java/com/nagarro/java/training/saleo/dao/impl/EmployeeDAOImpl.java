package com.nagarro.java.training.saleo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.java.training.saleo.dao.EmployeeDAO;
import com.nagarro.java.training.saleo.models.Employee;
import static com.nagarro.java.training.saleo.constants.Constants.*;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	SessionFactory factory;
	
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
	public Employee getEmployeeById(int employeeId) {
		
		Session session = factory.getCurrentSession();
		
		String getEmployeeByIdQuery = GET_CURRENT_EMPLOYEE_QUERY;

		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getEmployeeByIdQuery);

		query.setParameter("employeeId", employeeId);
		
		Employee currentEmployee = (Employee) query.getSingleResult();
		
		return currentEmployee;
	}

}
