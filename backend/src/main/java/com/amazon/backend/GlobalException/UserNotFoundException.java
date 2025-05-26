package com.amazon.backend.GlobalException;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String message)
	{
		super(message);
	}
	
	
}
