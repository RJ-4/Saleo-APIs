package com.nagarro.java.training.saleo.services;

import java.util.List;

import com.nagarro.java.training.saleo.models.Product;

public interface ProductService {

	public List<Product> getAllProducts();
	
	public Product getSingleProduct(int productId);
	
	public Product addNewProduct(Product newProduct);
	
	public Product updateProduct(Product updatedProduct, int productCode);
}
