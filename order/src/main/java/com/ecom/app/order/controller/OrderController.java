package com.ecom.app.order.controller;

import com.ecom.app.order.payload.OrderResponseDTO;
import com.ecom.app.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestHeader("X-User-ID") String userId) {
        return orderService.createOrder(userId)
                .map(orderResponseDTO -> new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
