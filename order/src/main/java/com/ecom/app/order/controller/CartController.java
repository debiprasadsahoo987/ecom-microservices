package com.ecom.app.order.controller;

import com.ecom.app.order.models.CartItem;
import com.ecom.app.order.payload.CartItemRequest;
import com.ecom.app.order.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId, @RequestBody CartItemRequest request) {
        if (!cartService.addToCart(userId, request)) {
            return new ResponseEntity<>("Unable to add product to the cart", HttpStatus.BAD_REQUEST);
        }
        ;
        return new ResponseEntity<>("Product added to the cart", HttpStatus.OK);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<String> removeFromCart(
            @RequestHeader("X-User-ID") String userId,
            @PathVariable Long productId
    ) {
        boolean deleted = cartService.deleteItemFromCart(userId, productId);
        return deleted ? new ResponseEntity<>("Item removed from the cart", HttpStatus.OK) : new ResponseEntity<>("Unable to remove product from the cart", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(@RequestHeader("X-User-ID") String userId) {
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }
}
