package com.nagarro.java.training.saleo.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "product_code")
	private Integer productCode;
	
	@Column(name = "product_quantity")
	private Integer productQuantity;
	
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
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

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productCode=" + productCode + ", productQuantity=" + productQuantity
				+ ", totalCost=" + totalCost + ", modeOfPayment=" + modeOfPayment + ", employeeId=" + employeeId
				+ ", customerId=" + customerId + ", orderDate=" + orderDate + ", orderTime=" + orderTime
				+ ", orderStatus=" + orderStatus + "]";
	}
	
}
