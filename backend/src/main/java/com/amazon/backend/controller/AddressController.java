package com.amazon.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.constant.AddressConstants;
import com.amazon.backend.entities.Address;
import com.amazon.backend.pojo.AddressAddData;
import com.amazon.backend.response.ApiResponse;
import com.amazon.backend.service.AddressService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("/Add")
	public ResponseEntity<ApiResponse<Address>> AddAddress(@RequestBody AddressAddData addressAddData) {

		Address address = addressService.AddAddressForShipping(addressAddData);

		ApiResponse<Address> address2 = new ApiResponse<Address>(true, AddressConstants.ADDRESS_ADDED_SUCCESSFULLY,
				address);

		return ResponseEntity.status(HttpStatus.OK).body(address2);
	}
	
	@GetMapping("/placedaddressesview/{userId}")
	public ResponseEntity<ApiResponse<List<Address>>> getAddresses(@PathVariable int userId) {
		List<Address> addresss = addressService.getAddress(userId);
		ApiResponse<List<Address>> address25 = new ApiResponse<List<Address>>(true,
				AddressConstants.ADDRESS_API_SUCCESSED, addresss);

		return ResponseEntity.status(HttpStatus.OK).body(address25);

	}
	@DeleteMapping("/deleteAddress/{addressId}/{userId}")
	
	public ResponseEntity<ApiResponse<String>> deleteAddress(@PathVariable int addressId, @PathVariable int userId)
	{
		addressService.deleteUsersAddess(addressId, userId);
		ApiResponse<String> address253 = new ApiResponse<String>(true,
				AddressConstants.ADDRESS_API_SUCCESSED, "");

		return ResponseEntity.status(HttpStatus.OK).body(address253);

	}
	
	
	
}
