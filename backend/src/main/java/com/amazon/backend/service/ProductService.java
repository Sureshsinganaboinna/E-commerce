package com.amazon.backend.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.GlobalException.ProductNotFoundException;
import com.amazon.backend.constant.ProductConstant;
import com.amazon.backend.entities.Productss;

import com.amazon.backend.repository.ProductssRepository;


import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductssRepository productssRepository;

	@Transactional
	public List<Productss> searchProductsFromDb(String word) {

		List<Productss> dbdta = productssRepository.searchProducts(word);

		return dbdta;
	}
	@Transactional
	public List<Productss> search(String word)
	{
		
	   List<Productss> dd =	productssRepository.searchProductsByWord(word);
		
		return dd;
	}
	
	@Transactional
	public List<Productss> searchProductsThatContainsWord(String word)
	{
		
	   List<Productss> dd =	productssRepository.searchProductsThatContainsWord(word);
		
		return dd;
	}
	
	
	
	
	
	
	
	public Productss getProductByGivenId(int id) {

		Optional<Productss> prodiOptional = productssRepository.findById(id);

		if (prodiOptional.isEmpty()) {
			logger.warn("product with ID {} not found", id);
			throw new ProductNotFoundException(ProductConstant.EXCEPTION_PRODUCT_NOT_FOUND, id);
		}

		return prodiOptional.get();

	}
	
	
	
	
	
	
	
	

}
