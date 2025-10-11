package com.app.ecom.controller;

import com.app.ecom.payload.CartItemRequest;
import com.app.ecom.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId, CartItemRequest request) {
        if (!cartService.addToCart(userId, request)) {
            return new ResponseEntity<>("Unable to add product to the cart", HttpStatus.BAD_REQUEST);
        }
        ;
        return new ResponseEntity<>("Product added to the cart", HttpStatus.CREATED);
    }
}
