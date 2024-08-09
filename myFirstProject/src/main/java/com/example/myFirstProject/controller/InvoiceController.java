package com.example.myFirstProject.controller;

import com.example.myFirstProject.model.Invoice;
import com.example.myFirstProject.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import com.example.myFirstProject.dto.InvoiceDetailDTO;
import com.example.myFirstProject.dto.InvoiceSummaryDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceRepository _invoiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<InvoiceSummaryDTO> getAllInvoices() {
        return _invoiceRepository.findAll().stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InvoiceDetailDTO getInvoiceById(@PathVariable int id) {
        return _invoiceRepository.findById(id)
                .map(invoice -> modelMapper.map(invoice, InvoiceDetailDTO.class))
                .orElse(null);
    }

    @PostMapping
    public Invoice insertInvoice(@RequestBody Invoice invoice) {
        return _invoiceRepository.save(invoice);
    }
}
