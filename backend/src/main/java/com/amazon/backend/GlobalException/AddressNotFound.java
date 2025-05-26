package com.amazon.backend.GlobalException;

public class AddressNotFound extends RuntimeException {

	private int userId;
	private int addressId;
	
	public AddressNotFound(String message, int userId, int addressId)
	{
		super(message);
		this.userId = userId;
		this.addressId = addressId;
	}
	
	public int getUserId()
	{
		return userId;
	}
	
	public int getAddressId()
	{
		return addressId;
	}
}
