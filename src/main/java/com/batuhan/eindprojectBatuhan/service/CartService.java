package com.batuhan.eindprojectBatuhan.service;

import com.batuhan.eindprojectBatuhan.model.Cart;
import com.batuhan.eindprojectBatuhan.model.CartItem;
import com.batuhan.eindprojectBatuhan.model.Product;
import com.batuhan.eindprojectBatuhan.model.User;
import com.batuhan.eindprojectBatuhan.repository.CartItemRepository;
import com.batuhan.eindprojectBatuhan.repository.CartRepository;
import com.batuhan.eindprojectBatuhan.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart getCartByCurrentUser(User user) {
        Optional<Cart> cartOptional = cartRepository.findByUser(user);
        if (cartOptional.isEmpty()) {
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
            return cart;
        }
        return cartOptional.get();
    }

    public void addProductToCart(User user, Long productId) {
        Cart cart = getCartByCurrentUser(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Zoek of het product al in de winkelmand zit
        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);
        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);  // Verhoog de hoeveelheid
            cartItemRepository.save(cartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(1);  // Voeg het product toe met hoeveelheid 1
            cartItemRepository.save(newCartItem);
        }
    }

    public void removeProductFromCart(User user, Long productId) {
        Cart cart = getCartByCurrentUser(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> cartItem = cartItemRepository.findByCartAndProduct(cart, product);
        cartItem.ifPresent(item -> {
            cart.getItems().remove(item);
            cartItemRepository.delete(item);
        });
    }


}
