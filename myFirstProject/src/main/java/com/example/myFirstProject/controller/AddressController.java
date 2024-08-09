package com.example.myFirstProject.controller;

import com.example.myFirstProject.model.Address;
import com.example.myFirstProject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressRepository _addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this._addressRepository = addressRepository;
    }

    @GetMapping
    public List<Address> getAddresses() {
        return _addressRepository.findAll();
    }

    @PostMapping
    public Address insertAddress(@RequestBody Address address) {
        return _addressRepository.save(address);
    }
}
