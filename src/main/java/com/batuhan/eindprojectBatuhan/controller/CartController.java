package com.batuhan.eindprojectBatuhan.controller;

import com.batuhan.eindprojectBatuhan.model.User;
import com.batuhan.eindprojectBatuhan.service.CartService;
import com.batuhan.eindprojectBatuhan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    // Haal het winkelmandje op voor de ingelogde gebruiker via Principal
    @GetMapping("/cart")
    public String viewCart(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username); // Gebruik de gebruikersnaam van de ingelogde gebruiker
        model.addAttribute("cart", cartService.getCartByCurrentUser(user)); // Haal het winkelmandje op voor de gebruiker
        return "cart"; // Dit verwijst naar de cart.html
    }

    // Voeg een product toe aan het winkelmandje van de ingelogde gebruiker
    @GetMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        cartService.addProductToCart(user, productId);
        return "redirect:/cart"; // Redirect naar de pagina met het winkelmandje
    }
}
