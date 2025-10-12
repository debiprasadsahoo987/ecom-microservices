package com.app.ecom.service;

import com.app.ecom.payload.OrderResponseDTO;

import java.util.Optional;

public interface OrderService {
    Optional<OrderResponseDTO> createOrder(String userId);
}
