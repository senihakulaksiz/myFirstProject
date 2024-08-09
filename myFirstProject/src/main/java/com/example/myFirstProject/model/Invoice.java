package com.example.myFirstProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime invoiceDate;
    private double totalAmount;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
