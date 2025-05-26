package com.amazon.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.constant.ProductConstant;
import com.amazon.backend.entities.Productss;
import com.amazon.backend.response.ApiResponse;
import com.amazon.backend.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<Productss>>> searchDataOfProductss(@RequestParam String word) {
		System.out.println(word);
		List<Productss> dbaa = productService.searchProductsFromDb(word);

		ApiResponse<List<Productss>> aaApiResponse = new ApiResponse<List<Productss>>(true,
				ProductConstant.PRODUCT_SEARCH_SUCCESS, dbaa);

		return ResponseEntity.status(HttpStatus.OK).body(aaApiResponse);
	}
	
	@GetMapping("/searchbyword")
	public ResponseEntity<ApiResponse<List<Productss>>> getProductsUsingWord(@RequestParam String word)
	{
		
	List<Productss> ss	  =  productService.search(word);
	
	ApiResponse<List<Productss>> proApiResponse = new ApiResponse<List<Productss>>(true, ProductConstant.PRODUCT_SEARCH_SUCCESS,ss);
	
	return ResponseEntity.status(HttpStatus.OK).body(proApiResponse);
		
	}
	
	@GetMapping("/searchby")
	public ResponseEntity<ApiResponse<List<Productss>>> getProductsUsingWords(@RequestParam String word)
	{
		System.out.println(word);
	List<Productss> ss	  =  productService.searchProductsThatContainsWord(word);
	
	ApiResponse<List<Productss>> proApiResponse = new ApiResponse<List<Productss>>(true, ProductConstant.PRODUCT_SEARCH_SUCCESS,ss);
	
	return ResponseEntity.status(HttpStatus.OK).body(proApiResponse);
		
	}
	
	

	@GetMapping("product/{id}")
	public ResponseEntity<ApiResponse<Productss>> getProductByid(@PathVariable int id) {

		Productss productss = productService.getProductByGivenId(id);
		ApiResponse<Productss> prodApiResponse = new ApiResponse<Productss>(true, ProductConstant.PRODUCT_API_SUCCESS,
				productss);

		return ResponseEntity.status(HttpStatus.OK).body(prodApiResponse);
	}
	
	

}
