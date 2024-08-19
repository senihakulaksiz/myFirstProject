package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.OrderDetailDTO;
import com.example.myFirstProject.dto.OrderSummaryDTO;

import java.util.List;

public interface OrderService {
    List<OrderSummaryDTO> getAllOrders();
    OrderDetailDTO getOrderById(Long id);
    OrderDetailDTO createOrder(OrderDetailDTO orderDTO);
    void deleteOrder(Long id);
}
