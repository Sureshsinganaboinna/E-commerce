package com.amazon.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amazon.backend.entities.Productss;

@Repository
public interface ProductssRepository extends JpaRepository<Productss, Integer> {

	@Procedure(procedureName = "sp_search_products")
	List<Productss> searchProducts(@Param("in_search_word") String word);
	
	
	@Procedure(procedureName = "GETProductDataByUsingWord")
	List<Productss> searchProductsByWord(@Param("word")     String word);
	
	/*
	@Procedure(procedureName = "GetProductWhichContainsWord")
	List<Productss> searchProductsThatContainsWord(@Param("word") String word);
	*/
	
	@Query(value = "CALL GetProductWhichContainsWord(:word)", nativeQuery = true)
	List<Productss> searchProductsThatContainsWord(@Param("word") String word);
	
	
}
