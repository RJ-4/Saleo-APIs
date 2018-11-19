package com.nagarro.java.training.saleo.dao;

import java.util.List;

import com.nagarro.java.training.saleo.models.Product;

public interface ProductDAO {

	public List<Product> getAllProducts();
	
	public List<Product> getSearchedProducts(String productProperty);
	
	public Product addNewProduct(Product newProduct);
	
	public Product updateProduct(Product updatedProduct, String productProperty);
	
	public void updateProductStock(int orderedItemQuantity, String productCode);
	
	public String getLatestProductCode();
	
	public List<Product> getLowStockProducts();
}
