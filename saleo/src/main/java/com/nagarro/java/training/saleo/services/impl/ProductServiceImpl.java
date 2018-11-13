package com.nagarro.java.training.saleo.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.saleo.dao.ProductDAO;
import com.nagarro.java.training.saleo.models.Product;
import com.nagarro.java.training.saleo.services.ProductService;

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

		return productDAO.getSingleProduct(productId);
	
	}

	@Override
	@Transactional
	public Product addNewProduct(Product newProduct) {

		return productDAO.addNewProduct(newProduct);
	
	}

	@Override
	@Transactional
	public Product updateProduct(Product updatedProduct, int productCode) {

		Product updatedExistingProduct = productDAO.updateProduct(updatedProduct, productCode);
	
		updatedExistingProduct.setProductCode(productCode);
		
		return updatedExistingProduct;
	}

}
