package com.app.ecom.controller;

import com.app.ecom.payload.CartItemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @PostMapping
    public ResponseEntity<Void> addToCart(@RequestHeader("X-user-Id") String userId, CartItemRequest request){
        cartService.addToCart(userId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
