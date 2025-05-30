package com.amazon.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.backend.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	
	List<Address> findByUserId(int userId);
	
	
}
