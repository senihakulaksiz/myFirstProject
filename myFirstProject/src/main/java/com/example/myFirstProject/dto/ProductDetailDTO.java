package com.example.myFirstProject.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private List<Long> orderItemIds;
}
