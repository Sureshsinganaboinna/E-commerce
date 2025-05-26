package com.amazon.backend.entities;

import java.time.LocalDateTime;

import com.amazon.backend.enums.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
public class Address {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	
	private int userId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String pincode;
	private String country;
	private Double latitude;
	private Double longitude;
	private Boolean isDefault;
	
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	private LocalDateTime createdOn = LocalDateTime.now();
	private LocalDateTime updatedOn = LocalDateTime.now();
	
	
	
}
