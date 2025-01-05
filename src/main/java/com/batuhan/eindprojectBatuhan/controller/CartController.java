package com.batuhan.eindprojectBatuhan.controller;

import com.batuhan.eindprojectBatuhan.model.Cart;
import com.batuhan.eindprojectBatuhan.model.Product;
import com.batuhan.eindprojectBatuhan.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCart(Model model) {
        Long userId = 1L; // Voor nu een hardcoded userId
        model.addAttribute("cart", cartService.getCartByUser(userId));
        return "cart";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId) {
        Long userId = 1L; // Voor nu een hardcoded userId
        cartService.addProductToCart(userId, productId);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        Long userId = 1L; // Voor nu een hardcoded userId
        cartService.removeProductFromCart(userId, productId);
        return "redirect:/cart";
    }
}
