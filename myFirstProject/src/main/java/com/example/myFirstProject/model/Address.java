package com.example.myFirstProject.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String state;
    private String city;
    private String street;
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}