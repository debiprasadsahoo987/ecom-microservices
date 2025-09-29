package com.app.ecom.service;

import com.app.ecom.payload.ProductRequestDTO;
import com.app.ecom.payload.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
}
