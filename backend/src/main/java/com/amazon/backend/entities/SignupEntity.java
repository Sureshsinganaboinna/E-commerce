package com.amazon.backend.entities;

import java.time.LocalDateTime;

import com.amazon.backend.enums.UserRole;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;


@Entity
@Data
@Table (name = "Usserrs", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class SignupEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String passwordHash;
	private String phoneNumber;
	private LocalDateTime createOn = LocalDateTime.now();
	private LocalDateTime updateOn = LocalDateTime.now();
	private Integer otp;
	@Enumerated(EnumType.STRING)
	private UserRole role;

}
