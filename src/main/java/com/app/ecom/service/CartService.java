package com.app.ecom.service;

import com.app.ecom.payload.CartItemRequest;

public interface CartService {
    boolean addToCart(String userId, CartItemRequest request);
}
