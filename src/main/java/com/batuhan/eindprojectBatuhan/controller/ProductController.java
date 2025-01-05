package com.batuhan.eindprojectBatuhan.controller;

import com.batuhan.eindprojectBatuhan.model.Product;
import com.batuhan.eindprojectBatuhan.service.ProductService;
import com.batuhan.eindprojectBatuhan.service.CartService;
import com.batuhan.eindprojectBatuhan.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    // Alle producten weergeven
    @GetMapping
    public String viewAllProducts(Model model) {
        // Haal alle producten op
        List<Product> products = productService.getAllProducts();

        // Voeg producten toe aan het model
        model.addAttribute("products", products);

        return "catalog"; // Verwijst naar catalog.html
    }

    // Producten filteren op categorie
    @GetMapping("/category/{categoryId}")
    public String viewProductsByCategory(@PathVariable Long categoryId, Model model) {
        model.addAttribute("products", productService.getProductsByCategory(categoryId));
        return "catalog";
    }

    // Product toevoegen aan winkelmandje
    @GetMapping("/cart/{cartId}/add/{productId}")
    public String addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.addProductToCart(cartId, productId);
        return "redirect:/cart/" + cartId;
    }
}
