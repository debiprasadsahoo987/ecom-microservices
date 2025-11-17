package com.ecom.app.product.services;

import com.ecom.app.product.exceptions.ResourceNotFoundException;
import com.ecom.app.product.models.Product;
import com.ecom.app.product.payload.ProductRequestDTO;
import com.ecom.app.product.payload.ProductResponseDTO;
import com.ecom.app.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (dto.getName() != null) product.setName(dto.getName());
        if (dto.getDescription() != null) product.setDescription(dto.getDescription());
        if (dto.getPrice() != null) product.setPrice(dto.getPrice());
        if (dto.getStockQuantity() != null) product.setStockQuantity(dto.getStockQuantity());
        if (dto.getCategory() != null) product.setCategory(dto.getCategory()); // String field
        if (dto.getImageUrl() != null) product.setImageUrl(dto.getImageUrl());

        Product saved = productRepository.save(product);
        return modelMapper.map(saved, ProductResponseDTO.class);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findByActiveTrue().stream().map(product -> modelMapper.map(product, ProductResponseDTO.class)).toList();
    }

    @Override
    public ProductResponseDTO deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    @Override
    public List<ProductResponseDTO> searchProducts(String keyword) {
        return productRepository.searchProductsByKeyword(keyword).stream().map(product -> modelMapper.map(product, ProductResponseDTO.class)).toList();
    }

    @Override
    public Optional<ProductResponseDTO> getProductById(String id) {
        return productRepository.findByIdAndActiveTrue(Long.valueOf(id))
                .map(product -> modelMapper.map(product, ProductResponseDTO.class));
    }
}
