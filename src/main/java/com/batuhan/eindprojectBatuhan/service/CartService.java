package com.batuhan.eindprojectBatuhan.service;

import com.batuhan.eindprojectBatuhan.model.Cart;
import com.batuhan.eindprojectBatuhan.model.Product;
import com.batuhan.eindprojectBatuhan.repository.CartRepository;
import com.batuhan.eindprojectBatuhan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCartByUser(Long userId) {
        return cartRepository.findById(userId)
                .orElseGet(() -> createNewCartForUser(userId));
    }

    private Cart createNewCartForUser(Long userId) {
        Cart cart = new Cart();
        cart.setId(userId);
        return cartRepository.save(cart);
    }

    public void addProductToCart(Long userId, Long productId) {
        Cart cart = getCartByUser(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = getCartByUser(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        cart.getProducts().remove(product);
        cartRepository.save(cart);
    }
}
