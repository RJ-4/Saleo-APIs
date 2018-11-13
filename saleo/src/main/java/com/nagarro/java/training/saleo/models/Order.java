package com.nagarro.java.training.saleo.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "total_cost")
	private Double totalCost;
	
	@Column(name = "mode_of_payment")
	private String modeOfPayment;
	
	@Column(name = "employee_id")
	private Integer employeeId;
	
	@Column(name = "customer_id")
	private Integer customerId;
	
	@Column(name = "order_date")
	private LocalDate orderDate;
	
	@Column(name = "order_time")
	private LocalTime orderTime;
	
	@Column(name = "order_status")
	private String orderStatus;


	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "employeeId")
	private Employee employee;

	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
				mappedBy = "order")
	private List<OrderDetails> orderDetails;
	
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public void addOrderDetail(OrderDetails newOrderDetail) {
		
		if(orderDetails == null) {
			
			orderDetails = new ArrayList<>();
		}
		
		orderDetails.add(newOrderDetail);
		
		newOrderDetail.setOrder(this);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", totalCost=" + totalCost + ", modeOfPayment=" + modeOfPayment
				+ ", employeeId=" + employeeId + ", customerId=" + customerId + ", orderDate=" + orderDate
				+ ", orderTime=" + orderTime + ", orderStatus=" + orderStatus + "]";
	}

}
