package com.batuhan.eindprojectBatuhan.controller;

import com.batuhan.eindprojectBatuhan.model.Category;
import com.batuhan.eindprojectBatuhan.model.Product;
import com.batuhan.eindprojectBatuhan.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/catalog")
    public String viewAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = productService.getAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "catalog";
    }

    @GetMapping("/catalog/category")
    public String viewProductsByCategory(@RequestParam Long categoryId, Model model) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        List<Category> categories = productService.getAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategoryId", categoryId);
        return "catalog";
    }
}