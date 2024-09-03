package com.example.myFirstProject.dto;

import lombok.Data;

@Data
public class PersonDetailDTO {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String role;
}
