package com.nagarro.java.training.saleo.services.impl;

import static com.nagarro.java.training.saleo.constants.Constants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.saleo.dao.ProductDAO;
import com.nagarro.java.training.saleo.exceptions.ProductNotFoundException;
import com.nagarro.java.training.saleo.models.Order;
import com.nagarro.java.training.saleo.models.Product;
import com.nagarro.java.training.saleo.services.ProductService;
import com.nagarro.java.training.saleo.token.AuthToken;
 
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	AuthToken auth;
	
	@Override
	@Transactional
	public List<Product> getAllProducts(String authToken) {

		auth.checkUserAuthorization(authToken);
		
		return productDAO.getAllProducts();
	}

	@Override
	@Transactional
	public Product getSingleProduct(String authToken, int productId) {

		auth.checkUserAuthorization(authToken);
		
		Product fetchedProduct = productDAO.getSingleProduct(productId);
		
		if(fetchedProduct == null) {
			
			throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
		
		} else {
			
			return fetchedProduct;
		}
	
	}

	@Override
	@Transactional
	public Product addNewProduct(String authToken, Product newProduct) {

		auth.checkUserAuthorization(authToken);
		
		return productDAO.addNewProduct(newProduct);
	
	}

	@Override
	@Transactional
	public Product updateProduct(String authToken, Product updatedProduct, int productCode) {

		auth.checkUserAuthorization(authToken);

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
	public void updateProductStock(String authToken, Order newOrder, int productCode) {

		auth.checkUserAuthorization(authToken);

		int orderedProductQuantity = newOrder.getProductQuantity();
		
		try {
		
			productDAO.updateProductStock(orderedProductQuantity, productCode);
	
		} catch (NullPointerException e) {

			throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
		}
	}
}
