package com.nagarro.java.training.saleo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.saleo.models.Product;
import com.nagarro.java.training.saleo.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
	
		return productService.getAllProducts();
	}
	
	@GetMapping("/products/{productCode}")
	public Product getSingleProduct(@PathVariable int productCode) {
		
		return productService.getSingleProduct(productCode);
	}
	
	@PostMapping("/products") 
	public Product addNewProduct(@RequestBody Product newProduct) {
		
		return productService.addNewProduct(newProduct);
	}
	
	@PutMapping("/products/{productCode}")
	public Product updateProduct(@RequestBody Product updatedProduct, @PathVariable int productCode) {
		
		return productService.updateProduct(updatedProduct, productCode);
	}
}
