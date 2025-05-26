package com.amazon.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDto {
	
	private int quantity;
	private int cartId;
	private int productId;

}
