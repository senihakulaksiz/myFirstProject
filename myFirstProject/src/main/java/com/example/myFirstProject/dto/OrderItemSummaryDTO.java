package com.example.myFirstProject.dto;

import lombok.Data;

@Data
public class OrderItemSummaryDTO {
    private Long id;
    private String itemName;
    private int quantity;
    private double price;
}
