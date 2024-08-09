package com.example.myFirstProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String item;
    private int quantity;
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}