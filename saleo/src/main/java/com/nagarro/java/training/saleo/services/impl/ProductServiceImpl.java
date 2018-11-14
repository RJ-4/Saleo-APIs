package com.nagarro.java.training.saleo.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.saleo.dao.ProductDAO;
import com.nagarro.java.training.saleo.exceptions.ProductNotFoundException;
import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.models.Product;
import com.nagarro.java.training.saleo.services.ProductService;
import static com.nagarro.java.training.saleo.constants.Constants.*;
 
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Override
	@Transactional
	public List<Product> getAllProducts() {

		return productDAO.getAllProducts();
	}

	@Override
	@Transactional
	public Product getSingleProduct(int productId) {

		Product fetchedProduct = productDAO.getSingleProduct(productId);
		
		if(fetchedProduct == null) {
			
			throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
		
		} else {
			
			return fetchedProduct;
		}
	
	}

	@Override
	@Transactional
	public Product addNewProduct(Product newProduct) {

		return productDAO.addNewProduct(newProduct);
	
	}

	@Override
	@Transactional
	public Product updateProduct(Product updatedProduct, int productCode) {

		Product updatedExistingProduct;
		
		try{
		
			updatedExistingProduct = productDAO.updateProduct(updatedProduct, productCode);
		
			updatedExistingProduct.setProductCode(productCode);
			
			return updatedExistingProduct;
		
		} catch(NullPointerException e) {
			
			throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
		}
	}

	@Override
	@Transactional
	public void updateProductStock(Order newOrder, int productCode) {

		int orderedProductQuantity = newOrder.getProductQuantity();
		
		try {
		
			productDAO.updateProductStock(orderedProductQuantity, productCode);
	
		} catch (NullPointerException e) {

			throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
		}
	}
}
