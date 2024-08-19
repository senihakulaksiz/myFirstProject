package com.example.myFirstProject.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDetailDTO {
    private int id;
    private LocalDateTime invoiceDate;
    private double totalAmount;
    private String issuedBy;
    private List<OrderItemDetailDTO> items;
}

