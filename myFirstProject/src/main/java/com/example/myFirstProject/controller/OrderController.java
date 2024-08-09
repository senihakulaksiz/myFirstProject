package com.example.myFirstProject.controller;

import com.example.myFirstProject.model.Order;
import com.example.myFirstProject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository _orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this._orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getOrders() {
        return _orderRepository.findAll();
    }

    @PostMapping
    public Order insertOrder(@RequestBody Order order) {
        return _orderRepository.save(order);
    }
}
