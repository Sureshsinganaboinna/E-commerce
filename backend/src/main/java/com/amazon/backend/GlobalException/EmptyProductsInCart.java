package com.amazon.backend.GlobalException;

public class EmptyProductsInCart extends RuntimeException {
	
	
	
	public EmptyProductsInCart(String message)
	{
		super(message);
	}

}
