package com.example.myFirstProject.repository;

import com.example.myFirstProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByStockQuantityGreaterThan(int quantity);
}
