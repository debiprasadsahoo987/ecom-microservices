package com.ecom.app.order.services;

import com.ecom.app.order.clients.ProductServiceClient;
import com.ecom.app.order.clients.UserServiceClient;
import com.ecom.app.order.models.CartItem;
import com.ecom.app.order.payload.CartItemRequest;
import com.ecom.app.order.payload.ProductResponseDTO;
import com.ecom.app.order.payload.UserResponseDTO;
import com.ecom.app.order.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
//    @Autowired
//    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductServiceClient productServiceClient;
    @Autowired
    private UserServiceClient userServiceClient;
//    @Autowired
//    private UserRepository userRepository;

    @Override
    public boolean addToCart(String userId, CartItemRequest request) {
        ProductResponseDTO productResponseDTO = productServiceClient.getProductDetails(String.valueOf(request.getProductId()));
        if (productResponseDTO==null || productResponseDTO.getStockQuantity() < request.getQuantity()) return false;

        UserResponseDTO userResponseDTO = userServiceClient.getUserDetails(String.valueOf(userId));
        if (userResponseDTO==null) return false;

        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, request.getProductId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
//            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
//            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    @Override
    public boolean deleteItemFromCart(String userId, Long productId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        if(cartItem != null) {
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    @Override
    public List<CartItem> getCart(String userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
