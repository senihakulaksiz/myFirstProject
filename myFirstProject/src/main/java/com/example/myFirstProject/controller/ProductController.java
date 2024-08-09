package com.example.myFirstProject.controller;

import com.example.myFirstProject.model.Product;
import com.example.myFirstProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository _productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this._productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getProducts() {
        return _productRepository.findAll();
    }

    @PostMapping
    public Product insertProduct(@RequestBody Product product) {
        return _productRepository.save(product);
    }
}