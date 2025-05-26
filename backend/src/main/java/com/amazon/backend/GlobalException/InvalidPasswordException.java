package com.amazon.backend.GlobalException;

public class InvalidPasswordException extends RuntimeException {
	
	   public InvalidPasswordException(String message)
	   {
		   super(message);
	   }

}
