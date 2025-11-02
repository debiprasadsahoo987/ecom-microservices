package com.ecom.app.order.services;

import com.ecom.app.order.models.CartItem;
import com.ecom.app.order.payload.CartItemRequest;

import java.util.List;

public interface CartService {
    boolean addToCart(Long userId, CartItemRequest request);

    boolean deleteItemFromCart(Long userId, Long productId);

    List<CartItem> getCart(Long userId);

    void clearCart(Long userId);
}
