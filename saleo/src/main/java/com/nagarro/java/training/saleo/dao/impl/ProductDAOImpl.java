package com.nagarro.java.training.saleo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.java.training.saleo.dao.ProductDAO;
import com.nagarro.java.training.saleo.models.Product;
import static com.nagarro.java.training.saleo.constants.Constants.*;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	SessionFactory factory;
	
	@Override
	public List<Product> getAllProducts() {
		
		Session session = factory.getCurrentSession();
		
		String getAllProductsQuery = GET_ALL_PRODUCTS_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getAllProductsQuery);
		
		@SuppressWarnings("unchecked")
		List<Product> getAllProducts = query.getResultList();
		
		return getAllProducts;
		
	}

	@Override
	public Product getSingleProduct(int productId) {

		Session session = factory.getCurrentSession();

		Product getSingleProduct = session.get(Product.class, productId);
		
		return getSingleProduct;
	}

	@Override
	public Product addNewProduct(Product newProduct) {
		
		Session session = factory.getCurrentSession();
		
		session.save(newProduct);
		
		return newProduct;
	}

	@Override
	public Product updateProduct(Product updatedProduct, int productCode) {
		
		Session session = factory.getCurrentSession();
		
		String updateProductQuery = UPDATE_PRODUCT_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(updateProductQuery);
		
		query.setParameter("productCode", productCode);
		query.setParameter("productName", updatedProduct.getProductName());
		query.setParameter("productDescription", updatedProduct.getProductDescription());
		query.setParameter("productStock", updatedProduct.getProductStock());
		query.setParameter("productUnitPrice", updatedProduct.getProductUnitPrice());
		
		query.executeUpdate();
		
		return updatedProduct;
	}

}
