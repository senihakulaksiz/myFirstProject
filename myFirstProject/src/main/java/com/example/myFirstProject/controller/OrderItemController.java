package com.example.myFirstProject.controller;

import com.example.myFirstProject.model.OrderItem;
import com.example.myFirstProject.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {
    @Autowired
    private final OrderItemRepository _orderItemRepository;

    public OrderItemController(OrderItemRepository orderItemRepository) {
        this._orderItemRepository = orderItemRepository;
    }

    @GetMapping
    public List<OrderItem> getOrderItems() {
        return _orderItemRepository.findAll();
    }

    @PostMapping
    public OrderItem insertOrderItem(@RequestBody OrderItem orderItem) {
        return _orderItemRepository.save(orderItem);
    }
}
