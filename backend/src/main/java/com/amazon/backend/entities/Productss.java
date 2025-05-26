package com.amazon.backend.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Productss {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;

	private String title;
	private String description;
	private Double price;
	private Integer Stock;
	private Integer categoryId;
	private String images;
	private LocalDate createdOn = LocalDate.now();
	@Column(name = "updated_on")
	private LocalDate updateOn = LocalDate.now();
	

}
