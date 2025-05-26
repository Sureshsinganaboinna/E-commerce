package com.amazon.backend.pojo;

import lombok.Data;

@Data
public class CartAdding {
	
	private int userId;
	private int productId;
	private int quantity;

}
