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
	public List<Product> getSearchedProducts(String authToken, String productProperty) {

		auth.checkUserAuthorization(authToken);
		
		List<Product> fetchedProduct = productDAO.getSearchedProducts(productProperty);
		
		if(fetchedProduct.size() == 0) {
			
			throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
		
		} else {
			
			return fetchedProduct;
		}
	
	}

	@Override
	@Transactional
	public Product addNewProduct(String authToken, Product newProduct) {

		auth.checkUserAuthorization(authToken);
		
		String newProductCode = generateProductCodeForNewProduct();
		
		newProduct.setProductCode(newProductCode);
		
		return productDAO.addNewProduct(newProduct);
	
	}

	@Override
	@Transactional
	public Product updateProduct(String authToken, Product updatedProduct, String productProperty) {

		auth.checkUserAuthorization(authToken);

		Product updatedExistingProduct;
		
		try{
		
			updatedExistingProduct = productDAO.updateProduct(updatedProduct, productProperty);
		
//			updatedExistingProduct.setProductCode(productProperty);
			
			return updatedExistingProduct;
		
		} catch(NullPointerException e) {
			
			throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
		}
	}

	@Override
	@Transactional
	public void updateProductStock(String authToken, Order newOrder, String productCode) {

		auth.checkUserAuthorization(authToken);

		int orderedProductQuantity = newOrder.getProductQuantity();
		
		try {
		
			productDAO.updateProductStock(orderedProductQuantity, productCode);
	
		} catch (NullPointerException e) {

			throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
		}
	}

	public String generateProductCodeForNewProduct() {
		
		String lastProductCode = productDAO.getLatestProductCode();
		
		int lastProductCodeNumber = Integer.parseInt(lastProductCode);
		
		int newProductCode = lastProductCodeNumber + 1;
		
		return "" + newProductCode;
	}

	@Override
	@Transactional
	public List<Product> getLowStockProducts(String authToken) {
	
		auth.checkUserAuthorization(authToken);
		
		return productDAO.getLowStockProducts();
	}
}
