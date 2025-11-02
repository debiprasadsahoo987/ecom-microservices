package com.ecom.app.order.services;

import com.ecom.app.order.models.CartItem;
import com.ecom.app.order.payload.CartItemRequest;
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
//    @Autowired
//    private UserRepository userRepository;

    @Override
    public boolean addToCart(Long userId, CartItemRequest request) {
//        Optional<Product> productOpt = productRepository.findById(request.getProductId());
//        if (productOpt.isEmpty()) return false;
//        Product product = productOpt.get();
//        if (product.getStockQuantity() < request.getQuantity()) return false;
//        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
//        if (userOpt.isEmpty()) return false;
//        User user = userOpt.get();

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
    public boolean deleteItemFromCart(Long userId, Long productId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        if(cartItem != null) {
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    @Override
    public List<CartItem> getCart(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
