package com.batuhan.eindprojectBatuhan.controller;

import com.batuhan.eindprojectBatuhan.model.Cart;
import com.batuhan.eindprojectBatuhan.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCart(@RequestParam Long userId, Model model) {
        Cart cart = cartService.getCartByUser(userId);
        model.addAttribute("cart", cart);
        return "cart"; // Verwijst naar cart.html
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.addProductToCart(userId, productId);
        return "redirect:/cart?userId=" + userId; // Redirect naar winkelmandje
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.removeProductFromCart(userId, productId);
        return "redirect:/cart?userId=" + userId; // Redirect naar winkelmandje
    }
}
