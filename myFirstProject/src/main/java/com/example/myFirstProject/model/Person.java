package com.example.myFirstProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "persons")
@Entity
public class Person {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String password;

    @OneToMany(mappedBy = "person")
    private List<Address> addresses;
}




