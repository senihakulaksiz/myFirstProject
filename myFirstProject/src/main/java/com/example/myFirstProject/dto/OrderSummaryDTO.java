package com.example.myFirstProject.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderSummaryDTO {
    private Long id;
    private String item;
    private int quantity;
    private LocalDateTime orderDate;
}
