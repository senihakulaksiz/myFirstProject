package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.AddressDetailDTO;
import com.example.myFirstProject.dto.AddressSummaryDTO;

import java.util.List;

public interface AddressService {
    List<AddressSummaryDTO> getAllAddresses();
    AddressDetailDTO getAddressById(Long id);
    AddressDetailDTO createAddress(AddressDetailDTO addressDTO);
    void deleteAddress(Long id);
}

