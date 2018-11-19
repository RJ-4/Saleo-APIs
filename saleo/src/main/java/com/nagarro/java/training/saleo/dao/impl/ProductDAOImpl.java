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
		
		@SuppressWarnings("unchecked")
		Query<Product> query = session.createQuery(getAllProductsQuery);
		
		List<Product> getAllProducts = query.getResultList();
		
		return getAllProducts;
		
	}

	@Override
	public List<Product> getSearchedProducts(String productProperty) {

		Session session = factory.getCurrentSession();

		String getSingleProductQuery = GET_SINGLE_PRODUCT_QUERY;
		
		@SuppressWarnings("unchecked")
		Query<Product> query = session.createQuery(getSingleProductQuery);
		
		query.setParameter(PRODUCT_SEARCH_PROPERTY, productProperty);
		
		//Product getSingleProduct = session.get(Product.class, productProperty);
		List<Product> getSearchedProductsList = query.getResultList();
		
		return getSearchedProductsList;
	}

	@Override
	public Product addNewProduct(Product newProduct) {
		
		Session session = factory.getCurrentSession();
		
		session.save(newProduct);
		
		return newProduct;
	}

	@Override
	public Product updateProduct(Product updatedProduct, String productProperty) throws NullPointerException {
		
		Session session = factory.getCurrentSession();
		
		String getSingleProductQuery = GET_SINGLE_PRODUCT_QUERY;
		
		@SuppressWarnings("unchecked")
		Query<Product> query = session.createQuery(getSingleProductQuery);
		
		query.setParameter(PRODUCT_SEARCH_PROPERTY, productProperty);
		
		Product currentProduct = query.getSingleResult();
		
		currentProduct.setProductName(updatedProduct.getProductName());
		
		currentProduct.setProductDescription(updatedProduct.getProductDescription());
		
		currentProduct.setProductStock(updatedProduct.getProductStock());
		
		currentProduct.setProductUnitPrice(updatedProduct.getProductUnitPrice());
		
		return currentProduct;
	}

	@Override
	public void updateProductStock(int orderedItemQuantity, String productCode) throws NullPointerException {

		Session session = factory.getCurrentSession();
		
		Product currentProduct = session.get(Product.class, productCode);
		
		int currentProductStock = currentProduct.getProductStock();
		
		currentProduct.setProductStock(currentProductStock - orderedItemQuantity);
	}

	@Override
	public String getLatestProductCode() {
		
		Session session = factory.getCurrentSession();
		
		String getLatestProductCodeQuery = GET_LAST_GENERATED_PRODUCT_CODE_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getLatestProductCodeQuery);
		
		String latestProductCode = (String) query.getSingleResult();
		
		return latestProductCode;
	}

	@Override
	public List<Product> getLowStockProducts() {
		
		Session session = factory.getCurrentSession();
		
		String getLowStockProductsQuery = GET_LOW_STOCK_PRODUCTS_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getLowStockProductsQuery);
		
		@SuppressWarnings("unchecked")
		List<Product> lowStockProducts = query.getResultList();
		
		return lowStockProducts;
	}

}
