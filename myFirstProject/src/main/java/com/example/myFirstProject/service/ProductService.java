package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.ProductDetailDTO;
import com.example.myFirstProject.dto.ProductSummaryDTO;

import java.util.List;

public interface ProductService {
    List<ProductSummaryDTO> getAllProducts();
    ProductDetailDTO getProductById(Long id);
    ProductDetailDTO createProduct(ProductDetailDTO productDTO);
    void deleteProduct(Long id);
}

