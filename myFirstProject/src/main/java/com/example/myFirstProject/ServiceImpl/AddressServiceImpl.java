package com.example.myFirstProject.ServiceImpl;

import com.example.myFirstProject.dto.AddressDetailDTO;
import com.example.myFirstProject.dto.AddressSummaryDTO;
import com.example.myFirstProject.model.Address;
import com.example.myFirstProject.repository.AddressRepository;
import com.example.myFirstProject.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AddressSummaryDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(address -> modelMapper.map(address, AddressSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDetailDTO getAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(addr -> modelMapper.map(addr, AddressDetailDTO.class)).orElse(null);
    }

    @Override
    public AddressDetailDTO createAddress(AddressDetailDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDetailDTO.class);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
