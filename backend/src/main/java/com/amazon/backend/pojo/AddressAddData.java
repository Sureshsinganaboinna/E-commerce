package com.amazon.backend.pojo;



import com.amazon.backend.enums.AddressType;

import lombok.Data;

@Data
public class AddressAddData {
	
	
	private int userId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String pinCode;
	private String country;
	private Double latitude;
	private Double longitude;
	private Boolean isDefault;
	private AddressType addressType;
	
	
}
