package com.app.ecom.controller;

import com.app.ecom.model.Product;
import com.app.ecom.payload.ProductRequestDTO;
import com.app.ecom.payload.ProductResponseDTO;
import com.app.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return new ResponseEntity<ProductResponseDTO>(productService.addProduct(productRequestDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        return new ResponseEntity<ProductResponseDTO>(productService.updateProduct(id, productRequestDTO), HttpStatus.OK);
    }
}
