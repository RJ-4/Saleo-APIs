package com.nagarro.java.training.saleo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_code")
	private Integer productCode;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_stock")
	private Integer productStock;
	
	@Column(name = "product_unit_price")
	private Double productUnitPrice;
	
	@Column(name = "product_description")
	private String productDescription;

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", productStock=" + productStock
				+ ", productPrice=" + productUnitPrice + ", productDescription=" + productDescription + "]";
	}

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public Double getProductUnitPrice() {
		return productUnitPrice;
	}

	public void setProductUnitPrice(Double productPrice) {
		this.productUnitPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
}
