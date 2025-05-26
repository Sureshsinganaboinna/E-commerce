package com.amazon.backend.GlobalException;

public class ProductNotFoundException extends RuntimeException {
	
	private int productId;
	
	public ProductNotFoundException(String message, int id)
	{
		
		super(message);
		this.productId = id;
		
	}
	
	public int getId()
	{
		return productId;
	}

}
