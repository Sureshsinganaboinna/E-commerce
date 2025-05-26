package com.amazon.backend.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_items")
public class CartItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cardItemId;
	private int cartId;
	private int productId;
	private int quantity;
	private LocalDate createdOn = LocalDate.now();
	@Column(name = "updated_on")
	private LocalDate updateOn = LocalDate.now();

}
