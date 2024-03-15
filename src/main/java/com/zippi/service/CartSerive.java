package com.zippi.service;

import com.zippi.Exception.CartException;
import com.zippi.Exception.CartItemException;
import com.zippi.Exception.MedItemException;
import com.zippi.Exception.UserException;
import com.zippi.model.Cart;
import com.zippi.model.CartItem;
import com.zippi.model.MedItem;
import com.zippi.model.User;
import com.zippi.request.AddCartItemRequest;
import com.zippi.request.UpdateCartItemRequest;

public interface CartSerive {

	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, MedItemException, CartException, CartItemException;

	public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

	public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

	public Long calculateCartTotals(Cart cart) throws UserException;
	
	public Cart findCartById(Long id) throws CartException;
	
	public Cart findCartByUserId(Long userId) throws CartException, UserException;
	

	

	

}
