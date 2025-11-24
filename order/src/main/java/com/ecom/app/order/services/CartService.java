package com.ecom.app.order.services;

import com.ecom.app.order.models.CartItem;
import com.ecom.app.order.payload.CartItemRequest;

import java.util.List;

public interface CartService {
    boolean addToCart(String userId, CartItemRequest request);

    boolean deleteItemFromCart(String userId, Long productId);

    List<CartItem> getCart(String userId);

    void clearCart(String userId);
}
