package com.nagarro.java.training.saleo.controller;

import static com.nagarro.java.training.saleo.constants.Constants.TOKEN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.saleo.models.Product;
import com.nagarro.java.training.saleo.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(@RequestHeader(TOKEN) String authToken) {
	
		return productService.getAllProducts(authToken);
	}
	
	@GetMapping("/products/{productCode}")
	public Product getSingleProduct(@RequestHeader(TOKEN) String authToken, @PathVariable int productCode) {
		
		return productService.getSingleProduct(authToken, productCode);
	}
	
	@PostMapping("/products") 
	public Product addNewProduct(@RequestHeader(TOKEN) String authToken, @RequestBody Product newProduct) {
		
		return productService.addNewProduct(authToken, newProduct);
	}
	
	@PutMapping("/products/{productCode}")
	public Product updateProduct(@RequestHeader(TOKEN) String authToken, 
									@RequestBody Product updatedProduct, @PathVariable int productCode) {
		
		return productService.updateProduct(authToken, updatedProduct, productCode);
	}
}
