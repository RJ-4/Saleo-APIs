package com.nagarro.java.training.saleo.token.impl;

import java.util.Base64;

import org.springframework.stereotype.Component;

import com.nagarro.java.training.saleo.exceptions.UserNotAuthorizedException;
import com.nagarro.java.training.saleo.token.AuthToken;
import static com.nagarro.java.training.saleo.constants.Constants.SLUG;
import static com.nagarro.java.training.saleo.constants.Constants.USER_NOT_AUTHORIZED_EXCEPTION_MESSAGE;

@Component
public class AuthTokenImpl implements AuthToken{

	
	@Override
	public String generateToken(int employeeId) {

		String employeeIdString = "" + employeeId;
		
		String employeeIdStringWithSlug = employeeIdString.concat(getSlug());
		
		byte[] inputInBytes = employeeIdStringWithSlug.getBytes();
		
		String token = Base64.getMimeEncoder().encodeToString(inputInBytes);
		
		return token;
	}

	@Override
	public String decodeToken(String token) {

		byte[] decodedTokenInBytes = Base64.getMimeDecoder().decode(token);
		
		String decodedTokenWithSlug = new String(decodedTokenInBytes);
		
		String slug = getSlug();
		
		int employeeIdLength = token.length() - slug.length();
		
		String decodedToken = decodedTokenWithSlug.substring(0, employeeIdLength);
		
		return decodedToken;
	
	}

	@Override
	public String getSlug() {

		String slug = "".concat(SLUG);
		
		return slug;
	
	}

	@Override
	public void checkUserAuthorization(String authToken) {

		if(authToken == "") {
			
			throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED_EXCEPTION_MESSAGE);
		}
	}
	
}
