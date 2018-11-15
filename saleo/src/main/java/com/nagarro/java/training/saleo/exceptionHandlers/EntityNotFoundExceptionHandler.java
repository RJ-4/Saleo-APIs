package com.nagarro.java.training.saleo.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nagarro.java.training.saleo.errors.ErrorResponse;
import com.nagarro.java.training.saleo.exceptions.UserNotAuthorizedException;

@ControllerAdvice
public class EntityNotFoundExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleUserNotAuthorizedException(UserNotAuthorizedException e) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		
		errorResponse.setErrorMessage(e.getMessage());
		
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		errorResponse.setErrorMessage(e.getMessage());
		
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	
	}
}
