package com.nagarro.java.training.saleo.controller;

import static com.nagarro.java.training.saleo.constants.Constants.TOKEN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.saleo.services.OrderService;

@RestController
@CrossOrigin
public class OrdersController {

	@Autowired
	OrderService orderService;
	
	@DeleteMapping("/orders/saved-for-later/{orderId}")
	public void deleteSavedForLaterOrder(@RequestHeader(TOKEN) String authToken,
											@PathVariable int orderId) {
		
		orderService.deleteSavedForLaterOrder(authToken, orderId);
		
	}

}
