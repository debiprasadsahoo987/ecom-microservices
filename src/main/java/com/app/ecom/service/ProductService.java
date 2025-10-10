package com.app.ecom.service;

import com.app.ecom.payload.ProductRequestDTO;
import com.app.ecom.payload.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO deleteProduct(Long id);

    List<ProductResponseDTO> searchProducts(String keyword);
}
