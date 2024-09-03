package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.ProductDetailDTO;
import com.example.myFirstProject.dto.ProductSummaryDTO;
import com.example.myFirstProject.model.Product;
import com.example.myFirstProject.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductSummaryDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductSummaryDTO> getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductSummaryDTO> getProductsInStock() {
        List<Product> products = productRepository.findByStockQuantityGreaterThan(0);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDetailDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product != null ? modelMapper.map(product, ProductDetailDTO.class) : null;
    }

    @Override
    public ProductDetailDTO createProduct(ProductDetailDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDetailDTO.class);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
