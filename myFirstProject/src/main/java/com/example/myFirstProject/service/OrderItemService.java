package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.OrderItemDetailDTO;
import com.example.myFirstProject.dto.OrderItemSummaryDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemSummaryDTO> getAllOrderItems();
    OrderItemDetailDTO getOrderItemById(Long id);
    OrderItemDetailDTO createOrderItem(OrderItemDetailDTO orderItemDTO);
    void deleteOrderItem(Long id);
}
