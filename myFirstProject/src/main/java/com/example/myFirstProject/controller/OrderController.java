package com.example.myFirstProject.controller;

import com.example.myFirstProject.dto.OrderDetailDTO;
import com.example.myFirstProject.dto.OrderSummaryDTO;
import com.example.myFirstProject.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderSummaryDTO> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/filterByDate")
    public List<OrderSummaryDTO> getOrdersByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return orderService.getOrdersByDateRange(startDate, endDate);
    }

    @GetMapping("/filterByPerson")
    public List<OrderSummaryDTO> getOrdersByPersonId(@RequestParam Long personId) {
        return orderService.getOrdersByPersonId(personId);
    }

    @GetMapping("/filterByDateAndPerson")
    public List<OrderSummaryDTO> getOrdersByDateRangeAndPersonId(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate, @RequestParam Long personId) {
        return orderService.getOrdersByDateRangeAndPersonId(startDate, endDate, personId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getOrderById(@PathVariable Long id) {
        OrderDetailDTO order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderDetailDTO> insertOrder(@RequestBody OrderDetailDTO orderDTO) {
        OrderDetailDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
