package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.models.Product;

public interface ProductService {

	public List<Product> getAllProducts(String authToken);
	
	public List<Product> getSearchedProducts(String authToken, String productProperty);
	
	public Product addNewProduct(String authToken, Product newProduct);
	
	public Product updateProduct(String authToken, Product updatedProduct, String productProperty);
	
	public void updateProductStock(String authToken, Order newOrder, String productCode);
	
	public String generateProductCodeForNewProduct();
}
