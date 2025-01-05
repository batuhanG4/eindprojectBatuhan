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

    // Haal het winkelwagentje op van de gebruiker
    public Cart getCartByUser(Long userId) {
        return cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    // Voeg een product toe aan het winkelwagentje
    public void addProductToCart(Long userId, Long productId) {
        Cart cart = getCartByUser(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    // Verwijder een product uit het winkelwagentje
    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = getCartByUser(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.getProducts().remove(product);
        cartRepository.save(cart);
    }
}
