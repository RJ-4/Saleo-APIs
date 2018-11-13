package com.nagarro.java.training.saleo.dao;

import java.util.List;

import com.nagarro.java.training.saleo.models.Product;

public interface ProductDAO {

	public List<Product> getAllProducts();
	
	public Product getSingleProduct(int productId);
	
	public Product addNewProduct(Product newProduct);
	
	public Product updateProduct(Product updatedProduct, int productCode);
}
