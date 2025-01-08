package com.batuhan.eindprojectBatuhan.controller;

import com.batuhan.eindprojectBatuhan.model.Cart;
import com.batuhan.eindprojectBatuhan.model.User;
import com.batuhan.eindprojectBatuhan.service.CartService;
import com.batuhan.eindprojectBatuhan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Cart cart = cartService.getCartByCurrentUser(user);
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.getTotalPrice());  // Voeg totaalprijs toe aan het model
        return "cart";
    }

    @GetMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        cartService.addProductToCart(user, productId);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{productId}")
    public String removeProductFromCart(@PathVariable Long productId, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        cartService.removeProductFromCart(user, productId);
        return "redirect:/cart";
    }
}
