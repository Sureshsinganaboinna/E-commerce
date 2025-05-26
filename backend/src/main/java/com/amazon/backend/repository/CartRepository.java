package com.amazon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.amazon.backend.entities.Cart;
import com.amazon.backend.entities.Productss;

import java.util.List;
import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart, Integer> {

	
	 Optional<Cart> findByUserId(int userId);
	 
	 @Query(value = "select p.* from productss as p INNER JOIN cart_items as c\r\n"
	 		+ " ON p.product_id = c.product_id where c.cart_id in (SELECT cart_id FROM carts where user_id = :userId)", nativeQuery = true)
	 List<Productss> getListOfProducts(@Param("userId") int userId);
	
	 
	 
	 @Query(value = "CALL sp_cart_view_basedon_user_id(:in_user_id)", nativeQuery = true)
	 List<Object[]> getRawCartProducts(@Param("in_user_id") int userId);
	 
	/* 
	 @Procedure(procedureName = "sp_cart_view_basedon_user_id")
	 List<Productss> getListOfProductsUsingSp(@Param("in_user_id") int userId);
	 */
	 
	 @Query(value = "CALL sp_update_product_quantity(:in_quantity,:in_cart_id,:in_product_id)", nativeQuery = true )
	 Object updateProductQuantity(
			@Param("in_quantity") int quantity,
			@Param("in_cart_id") int cartId,
			@Param("in_product_id") int productId
			 );
	 
	 
	 
	
}
