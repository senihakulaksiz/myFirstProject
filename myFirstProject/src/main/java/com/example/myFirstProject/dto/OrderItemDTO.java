package com.example.myFirstProject.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private int id;
    private String itemName;
    private int quantity;
    private double price;
}
