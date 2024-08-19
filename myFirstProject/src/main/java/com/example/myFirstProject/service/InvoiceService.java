package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.InvoiceDetailDTO;
import com.example.myFirstProject.dto.InvoiceSummaryDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceDetailDTO createInvoice(InvoiceDetailDTO invoiceDetailDTO);
    InvoiceDetailDTO getInvoiceById(Long id);
    List<InvoiceSummaryDTO> getAllInvoices();
    void deleteInvoice(Long id);
}


