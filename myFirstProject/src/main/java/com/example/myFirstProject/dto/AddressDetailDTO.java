package com.example.myFirstProject.dto;

import lombok.Data;

@Data
public class AddressDetailDTO {
    private Long id;
    private String state;
    private String city;
    private String street;
    private String postalCode;
    private Long personId;
}

