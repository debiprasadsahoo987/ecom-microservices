package com.app.ecom.service;

import com.app.ecom.model.CartItem;
import com.app.ecom.payload.CartItemRequest;

import java.util.List;

public interface CartService {
    boolean addToCart(String userId, CartItemRequest request);

    boolean deleteItemFromCart(String userId, Long productId);

    List<CartItem> getCart(String userId);

    void clearCart(String userId);
}
