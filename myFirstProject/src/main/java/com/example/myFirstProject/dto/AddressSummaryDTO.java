package com.example.myFirstProject.dto;

import lombok.Data;

@Data
public class AddressSummaryDTO {
    private Long id;
    private String state;
    private String city;
    private String street;
    private String postalCode;
}
