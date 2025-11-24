package com.ecom.app.order.clients;

import com.ecom.app.order.payload.UserResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface UserServiceClient {
    @GetExchange("/api/users/{id}")
    UserResponseDTO getUserDetails(@PathVariable String id);
}
