package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.OrderDetailDTO;
import com.example.myFirstProject.dto.OrderSummaryDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<OrderSummaryDTO> getAllOrders();
    List<OrderSummaryDTO> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<OrderSummaryDTO> getOrdersByPersonId(Long personId);
    List<OrderSummaryDTO> getOrdersByDateRangeAndPersonId(LocalDateTime startDate, LocalDateTime endDate, Long personId);
    OrderDetailDTO getOrderById(Long id);
    OrderDetailDTO createOrder(OrderDetailDTO orderDTO);
    void deleteOrder(Long id);

}
