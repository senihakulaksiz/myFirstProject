package com.example.myFirstProject.controller;

import com.example.myFirstProject.dto.InvoiceDetailDTO;
import com.example.myFirstProject.dto.InvoiceSummaryDTO;
import com.example.myFirstProject.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<InvoiceSummaryDTO> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDetailDTO> getInvoiceById(@PathVariable Long id) {
        InvoiceDetailDTO invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<InvoiceDetailDTO> createInvoice(@RequestBody InvoiceDetailDTO invoiceDetailDTO) {
        InvoiceDetailDTO createdInvoice = invoiceService.createInvoice(invoiceDetailDTO);
        return ResponseEntity.ok(createdInvoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
