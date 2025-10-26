package com.ecom.app.order.services;

import com.ecom.app.order.payload.OrderResponseDTO;

import java.util.Optional;

public interface OrderService {
    Optional<OrderResponseDTO> createOrder(String userId);
}
