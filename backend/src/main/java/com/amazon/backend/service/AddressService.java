package com.amazon.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.GlobalException.AddressNotFound;
import com.amazon.backend.constant.AddressConstants;
import com.amazon.backend.entities.Address;
import com.amazon.backend.pojo.AddressAddData;
import com.amazon.backend.repository.AddressRepository;

@Service
public class AddressService {
	
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	public Address AddAddressForShipping(AddressAddData addressAddData)
	{
		
		Address address = new Address();
		address.setUserId(addressAddData.getUserId());
		address.setAddressLine1(addressAddData.getAddressLine1());
		address.setAddressLine2(addressAddData.getAddressLine2());
		address.setCity(addressAddData.getCity());
		address.setState(addressAddData.getState());
		address.setCountry(addressAddData.getCountry());
		address.setPincode(addressAddData.getPinCode());
		address.setLatitude(addressAddData.getLatitude());
		address.setLongitude(addressAddData.getLongitude());
		address.setIsDefault(addressAddData.getIsDefault());
		address.setAddressType(addressAddData.getAddressType());
		
		Address address2    = addressRepository.save(address);
		
		return address2;
		
	}

	public List<Address> getAddress(int userId) {

		List<Address> addresses = addressRepository.findByUserId(userId);

		return addresses;
	}

	
	public void deleteUsersAddess(int addressId, int userId)
	{
		   Optional<Address> adresss = addressRepository.findById(addressId);
		   
		   if(adresss.isEmpty())
		   {
			   throw new AddressNotFound(AddressConstants.EXCEPTION_ADDRESS_NOT_EXCEPTION, userId, addressId);
		   }
		   
		   addressRepository.deleteById(adresss.get().getAddressId());
		   
	}
	
	
	
}
