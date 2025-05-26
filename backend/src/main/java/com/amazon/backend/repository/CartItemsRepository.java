package com.amazon.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amazon.backend.entities.CartItems;
import com.amazon.backend.entities.Productss;

public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
	
@Query(value = "select p.* from productss as p INNER JOIN cart_items as c ON p.product_id = c.product_id where c.cart_id = :cardId", nativeQuery = true)
List<Productss> getListOfItemsAfterAddedToCart(@Param("cardId") int cardId);



}
