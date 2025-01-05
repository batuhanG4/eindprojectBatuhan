package com.batuhan.eindprojectBatuhan.controller;

import com.batuhan.eindprojectBatuhan.model.Product;
import com.batuhan.eindprojectBatuhan.service.ProductService;
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

    @GetMapping
    public String viewAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "catalog"; // Verwijst naar catalog.html
    }

    @GetMapping("/category")
    public String viewProductsByCategory(@RequestParam Long categoryId, Model model) {
        model.addAttribute("products", productService.getProductsByCategory(categoryId));
        return "catalog";
    }
}
