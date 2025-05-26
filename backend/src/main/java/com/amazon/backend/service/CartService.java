package com.amazon.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.GlobalException.EmptyProductsInCart;
import com.amazon.backend.constant.CartConstants;
import com.amazon.backend.dto.CartItemDto;
import com.amazon.backend.entities.Cart;
import com.amazon.backend.entities.CartItems;
import com.amazon.backend.entities.Productss;
import com.amazon.backend.pojo.CartAdding;
import com.amazon.backend.pojo.UpdateQuantity;
import com.amazon.backend.repository.CartItemsRepository;
import com.amazon.backend.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemsRepository cartItemsRepository;

	public List<Productss> AddingItemsToCard(CartAdding cartAdding) {
		System.out.println(cartAdding.getUserId());
		Optional<Cart> sss = cartRepository.findByUserId(cartAdding.getUserId());

		if (sss.isEmpty()) {
			Cart cart = new Cart();
			cart.setUserId(cartAdding.getUserId());

			Cart cart2 = cartRepository.save(cart);

			CartItems cartItems = new CartItems();
			cartItems.setCartId(cart2.getCartId());
			cartItems.setProductId(cartAdding.getProductId());
			cartItems.setQuantity(cartAdding.getQuantity());

			CartItems cartItems2 = cartItemsRepository.save(cartItems);
			List<Productss> listofproducts1 = cartItemsRepository
					.getListOfItemsAfterAddedToCart(cartItems2.getCartId());
			return listofproducts1;

		}

		else {
			Cart cartdb = sss.get();
			CartItems cartItems = new CartItems();
			cartItems.setCartId(cartdb.getCartId());
			cartItems.setProductId(cartAdding.getProductId());
			cartItems.setQuantity(cartAdding.getQuantity());

			CartItems cartItems1 = cartItemsRepository.save(cartItems);
			List<Productss> listofproducts = cartItemsRepository.getListOfItemsAfterAddedToCart(cartItems1.getCartId());
			return listofproducts;
		}

	}
	
	@Transactional
	public List<Productss> getProducts(int userId) {
		// List<Productss> getProductssFromDb =
		// cartRepository.getListOfProducts(userId);
		List<Productss> getProductssFromDb = cartRepository.getListOfProducts(userId);

		if (getProductssFromDb.isEmpty()) {
			throw new EmptyProductsInCart(CartConstants.EMPTY_PRODUCTS_IN_CART);
		}

		return getProductssFromDb;

	}

	public void updateQuantity(int cartItemId, UpdateQuantity updateQuantity) {
		Optional<CartItems> cartItemIddata = cartItemsRepository.findById(cartItemId);

		
		CartItems cartItems = cartItemIddata.get();

		cartItems.setQuantity(updateQuantity.getQuantity());

		cartItemsRepository.save(cartItems);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public CartItemDto updateQuantityProducts(CartAdding cartAdding)

	{
		Optional<Cart> cartData = cartRepository.findByUserId(cartAdding.getUserId());
		System.out.println(cartData.get());
		  Object updateData = cartRepository.updateProductQuantity(cartAdding.getQuantity(), cartData.get().getCartId(),
				cartAdding.getProductId());
		  
		  Object[] castObjects = (Object[]) updateData;
		  
		 CartItemDto cartItemDto = new CartItemDto( (Integer) castObjects[0] , (Integer) castObjects[1], (Integer) castObjects[2]);
		 
		 return cartItemDto;
	

	}
	
	
	

	public void deleteItemFromCart(int cartItemId) {
		cartItemsRepository.deleteById(cartItemId);

	}

}
