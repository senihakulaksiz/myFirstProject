package com.example.myFirstProject.controller;

import com.example.myFirstProject.dto.OrderItemSummaryDTO;
import com.example.myFirstProject.model.OrderItem;
import com.example.myFirstProject.repository.OrderItemRepository;
import com.example.myFirstProject.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {
    @Autowired
    private final OrderItemRepository _orderItemRepository;
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemRepository orderItemRepository, OrderItemService orderItemService) {
        this._orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItem> getOrderItems() {
        return _orderItemRepository.findAll();
    }

    @PostMapping
    public OrderItem insertOrderItem(@RequestBody OrderItem orderItem) {
        return _orderItemRepository.save(orderItem);
    }

    @GetMapping("/quantity-greater-than")
    public List<OrderItemSummaryDTO> getOrderItemsByQuantityGreaterThan(@RequestParam int quantity) {
        return orderItemService.getOrderItemsByQuantityGreaterThan(quantity);
    }

    @GetMapping("/price-range")
    public List<OrderItemSummaryDTO> getOrderItemsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return orderItemService.getOrderItemsByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/by-order")
    public List<OrderItemSummaryDTO> getOrderItemsByOrderId(@RequestParam Long orderId) {
        return orderItemService.getOrderItemsByOrderId(orderId);
    }

    @GetMapping("/by-product")
    public List<OrderItemSummaryDTO> getOrderItemsByProductId(@RequestParam Long productId) {
        return orderItemService.getOrderItemsByProductId(productId);
    }
}
