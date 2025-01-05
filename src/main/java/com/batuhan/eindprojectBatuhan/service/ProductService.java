package com.batuhan.eindprojectBatuhan.service;

import com.batuhan.eindprojectBatuhan.model.Product;
import com.batuhan.eindprojectBatuhan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Voeg de methode toe om producten op te halen op basis van categorie
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}

