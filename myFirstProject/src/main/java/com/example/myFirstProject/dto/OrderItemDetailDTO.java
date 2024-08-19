package com.example.myFirstProject.dto;

import lombok.Data;

@Data
public class OrderItemDetailDTO {
    private Long id;
    private int quantity;
    private double price;
    private Long orderId;
    private Long productId;
}
