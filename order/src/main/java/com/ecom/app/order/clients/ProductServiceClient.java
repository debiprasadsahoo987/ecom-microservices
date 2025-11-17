package com.ecom.app.order.clients;

import com.ecom.app.order.payload.ProductResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@HttpExchange
public interface ProductServiceClient {
    @GetExchange("/api/products/{id}")
    ProductResponseDTO getProductDetails(@PathVariable String id);
}
