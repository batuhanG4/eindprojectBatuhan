package com.batuhan.eindprojectBatuhan.repository;

import com.batuhan.eindprojectBatuhan.model.Cart;
import com.batuhan.eindprojectBatuhan.model.CartItem;
import com.batuhan.eindprojectBatuhan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Vind een CartItem op basis van Cart en Product
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
