package com.nagarro.java.training.saleo.token;

public interface AuthToken {

	public String generateToken(int employeeId);
	
	public String decodeToken(String token);
	
	public String getSlug();
}
