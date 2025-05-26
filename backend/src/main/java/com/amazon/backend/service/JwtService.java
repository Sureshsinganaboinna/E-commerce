package com.amazon.backend.service;


import java.security.Key;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazon.backend.entities.SignupEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



@Service
public class JwtService {
	
	/*
	 * Secrete Key
	 * Add dependencies
	 * Decide what to store in that token
	 * Decide expiry duration -- like 24 hours
	 * Generate encrypted key using secrete key
	 * Generate Jwt Token
	 */
	
	@Value("${jwt.secret.key}")
	private String jwtSecreteKey;
	
	private int JWT_TOKEN_VALIDITY = 48*60*60*1000;
	
	
	private Key generateSecretKey()
	{
		//System.out.println(Keys.hmacShaKeyFor(jwtSecreteKey.getBytes()));
		return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes());
		
	}
	
	public String generateJwtToken(SignupEntity userData)
	{
		Date tokengeneratTime = new Date();
		
		Date expirydate = new Date(tokengeneratTime.getTime()+JWT_TOKEN_VALIDITY);
		
		Map<String, Object> tokenData = new HashMap<String, Object>();
		tokenData.put("id", userData.getUserId());
		tokenData.put("name", userData.getFirstName());
		tokenData.put("email", userData.getEmail());
		
	String  jwttoken  =	Jwts.builder().claims().add(tokenData).and()
		.subject(userData.getEmail())
		.issuedAt(tokengeneratTime)
		.expiration(expirydate)
		.signWith(generateSecretKey()).compact();	
		return jwttoken;
		
	}

}
