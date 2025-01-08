package com.batuhan.eindprojectBatuhan.service;

import com.batuhan.eindprojectBatuhan.model.Cart;
import com.batuhan.eindprojectBatuhan.model.Product;
import com.batuhan.eindprojectBatuhan.model.User;
import com.batuhan.eindprojectBatuhan.repository.CartRepository;
import com.batuhan.eindprojectBatuhan.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getCartByCurrentUser(User user) {
        // Probeer het winkelmandje van de gebruiker te vinden
        Optional<Cart> cartOptional = cartRepository.findByUser(user);

        // Als het winkelmandje niet bestaat, maak een nieuwe aan
        if (cartOptional.isEmpty()) {
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart); // Sla het nieuwe winkelmandje op in de database
            return cart;
        }

        // Als het winkelmandje wel bestaat, retourneer het
        return cartOptional.get();
    }

    public void addProductToCart(User user, Long productId) {
        Cart cart = getCartByCurrentUser(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }
}
