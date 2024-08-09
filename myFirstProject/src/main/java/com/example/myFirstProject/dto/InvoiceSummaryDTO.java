package com.example.myFirstProject.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InvoiceSummaryDTO {
    private LocalDateTime invoiceDate;
    private double totalAmount;
    private String issuedBy;
}

