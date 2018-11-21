package com.nagarro.java.training.saleo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.java.training.saleo.dao.CustomerDAO;
import com.nagarro.java.training.saleo.models.Customer;
import static com.nagarro.java.training.saleo.constants.Constants.*;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory factory;
	
	@Override
	public List<Customer> getAllCustomers() {
	
		Session session = factory.getCurrentSession();
		
		String getAllCustomersQuery = GET_ALL_CUSTOMERS_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getAllCustomersQuery);
		
		@SuppressWarnings("unchecked")
		List<Customer> customers = query.getResultList();
		
		return customers;
	
	}

	@Override
	public List<Customer> searchCustomer(String cutomerProperty) throws Exception {

		Session session = factory.getCurrentSession();
		
		String searchCustomerQuery = SEARCH_CUSTOMER_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(searchCustomerQuery);
		
		query.setParameter(CUSTOMER_SEARCH_PROPERTY, "%" + cutomerProperty + "%");
		
		@SuppressWarnings("unchecked")
		List<Customer> foundCustomers = query.getResultList();
		
		return foundCustomers;
	}

	@Override
	public Customer saveCustomer(Customer newCustomer) {
		
		Session session = factory.getCurrentSession();
		
		session.save(newCustomer);		
	
		return newCustomer;
	}

	@Override
	public Customer updateCustomer(Customer updatedCustomer, String customerId) throws NullPointerException {
		
		Session session = factory.getCurrentSession();
		
		Customer selectedCustomer = session.get(Customer.class, customerId);
		
		selectedCustomer.setCustomerName(updatedCustomer.getCustomerName());
		
		selectedCustomer.setCustomerMobileNo(updatedCustomer.getCustomerMobileNo());
		
		selectedCustomer.setCustomerEmailId(updatedCustomer.getCustomerEmailId());
		
		return selectedCustomer;
	}

	@Override
	public String getLatestCustomerId() {
		
		Session session = factory.getCurrentSession();
		
		String getLatestCustomerIdQuery = GET_LAST_GENERATED_CUSTOMER_ID_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getLatestCustomerIdQuery);
		
		String lastCustomerId = (String) query.getSingleResult();
		
		return lastCustomerId;
	}

}
