package com.amazon.backend.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.backend.entities.SignupEntity;

public interface SignupRespository extends JpaRepository<SignupEntity, Integer> {
	
    Optional<SignupEntity> findByemail(String email);

}
