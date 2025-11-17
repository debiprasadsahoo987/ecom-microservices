package com.ecom.app.product.services;

import com.ecom.app.product.payload.ProductRequestDTO;
import com.ecom.app.product.payload.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO deleteProduct(Long id);

    List<ProductResponseDTO> searchProducts(String keyword);

    Optional<ProductResponseDTO> getProductById(String id);
}
