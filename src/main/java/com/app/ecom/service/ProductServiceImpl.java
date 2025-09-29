package com.app.ecom.service;

import com.app.ecom.exceptions.ResourceNotFoundException;
import com.app.ecom.model.Product;
import com.app.ecom.payload.ProductRequestDTO;
import com.app.ecom.payload.ProductResponseDTO;
import com.app.ecom.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponseDTO.class);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id)); // 404 if not found

        // Null-safe field updates
        if (dto.getName() != null)           product.setName(dto.getName());
        if (dto.getDescription() != null)    product.setDescription(dto.getDescription());
        if (dto.getPrice() != null)          product.setPrice(dto.getPrice());
        if (dto.getStockQuantity() != null)  product.setStockQuantity(dto.getStockQuantity());
        if (dto.getCategory() != null)       product.setCategory(dto.getCategory()); // String field
        if (dto.getImageUrl() != null)       product.setImageUrl(dto.getImageUrl());

        Product saved = productRepository.save(product);
        return modelMapper.map(saved, ProductResponseDTO.class);
    }
}
