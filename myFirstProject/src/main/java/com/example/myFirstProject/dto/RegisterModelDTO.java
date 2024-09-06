package com.example.myFirstProject.dto;

import lombok.Data;

@Data
public class RegisterModelDTO {
    private String name;
    private String surname;
    private String password;
    private String role;
}
