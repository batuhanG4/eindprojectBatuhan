package com.batuhan.eindprojectBatuhan.controller;
/*
import com.batuhan.eindprojectBatuhan.service.ProductService;
import com.batuhan.eindprojectBatuhan.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    // Toon alle producten in de catalogus
    @GetMapping
    public String viewAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "catalog";
    }

    // Toon producten op basis van categorie
    @GetMapping("/category/{categoryId}")
    public String viewProductsByCategory(@PathVariable Long categoryId, Model model) {
        model.addAttribute("products", productService.getProductsByCategory(categoryId));
        return "catalog";
    }

    // Voeg een product toe aan het winkelwagentje
    @PostMapping("/cart/{cartId}/add/{productId}")
    public String addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.addProductToCart(cartId, productId);
        return "redirect:/cart/" + cartId;
    }
}*/
