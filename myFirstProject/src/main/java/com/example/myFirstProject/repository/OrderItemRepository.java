package com.example.myFirstProject.repository;

import com.example.myFirstProject.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByQuantityGreaterThan(int quantity);
    List<OrderItem> findByPriceBetween(double minPrice, double maxPrice);
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductId(Long productId);
}
