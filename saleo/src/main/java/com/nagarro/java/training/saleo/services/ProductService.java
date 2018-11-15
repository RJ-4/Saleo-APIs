package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.models.Product;

public interface ProductService {

	public List<Product> getAllProducts(String authToken);
	
	public Product getSingleProduct(String authToken, int productId);
	
	public Product addNewProduct(String authToken, Product newProduct);
	
	public Product updateProduct(String authToken, Product updatedProduct, int productCode);
	
	public void updateProductStock(String authToken, Order newOrder, int productCode);
}
