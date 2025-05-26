package com.amazon.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.constant.CartConstants;
import com.amazon.backend.dto.CartItemDto;
import com.amazon.backend.entities.Cart;
import com.amazon.backend.entities.CartItems;
import com.amazon.backend.entities.Productss;
import com.amazon.backend.pojo.CartAdding;
import com.amazon.backend.pojo.UpdateQuantity;
import com.amazon.backend.response.ApiResponse;
import com.amazon.backend.service.CartService;

@RestController
@RequestMapping("/Cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/ItemaddingtoCart")
	public ResponseEntity<ApiResponse<List<Productss>>> name(@RequestBody CartAdding cartAdding) {
		List<Productss> db = cartService.AddingItemsToCard(cartAdding);

		ApiResponse<List<Productss>> cartssApiResponse = new ApiResponse<List<Productss>>(true,
				CartConstants.CART_API_SUCCESS, db);

		return ResponseEntity.status(HttpStatus.OK).body(cartssApiResponse);

	}

	@GetMapping("/view/{userId}")
	public ResponseEntity<ApiResponse<List<Productss>>> getProductsOfcart(@PathVariable int userId) {
		List<Productss> dbdataList = cartService.getProducts(userId);
		ApiResponse<List<Productss>> cartseeApiResponse = new ApiResponse<List<Productss>>(true,
				CartConstants.CART_API_SUCCESS, dbdataList);
		return ResponseEntity.status(HttpStatus.OK).body(cartseeApiResponse);
	}

	@PutMapping("/updateproductquantity/{cartItemId}")
	public void updateQuantityOfProduct(@PathVariable int cartItemId, @RequestBody UpdateQuantity updateQuantity) {
		cartService.updateQuantity(cartItemId, updateQuantity);
	}

	@PutMapping("/quantity")
	public ResponseEntity<ApiResponse<CartItemDto>> updateProductQuantity(@RequestBody CartAdding cartAdding) {
		CartItemDto cartItemDto = cartService.updateQuantityProducts(cartAdding);
		ApiResponse<CartItemDto> cartseeApiResponse = new ApiResponse<CartItemDto>(true, CartConstants.CART_API_SUCCESS,
				cartItemDto);
		return ResponseEntity.status(HttpStatus.OK).body(cartseeApiResponse);
	}

	@DeleteMapping("/deleteItemFromCart/{cartItemId}")
	public ResponseEntity<ApiResponse<String>> deleteItemFromCart(@PathVariable int cartItemId) {
		cartService.deleteItemFromCart(cartItemId);
		ApiResponse<String> deleteItemFromCart = new ApiResponse<String>(true, CartConstants.CART_API_SUCCESS, "");
		return ResponseEntity.status(HttpStatus.OK).body(deleteItemFromCart);

	}

}
