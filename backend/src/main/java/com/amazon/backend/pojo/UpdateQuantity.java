package com.amazon.backend.pojo;

import lombok.Data;

@Data
public class UpdateQuantity {
	
	private int cartId;
	private int productId;
	private int quantity;

}
