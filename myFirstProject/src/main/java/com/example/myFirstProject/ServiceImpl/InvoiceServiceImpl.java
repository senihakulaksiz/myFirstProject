package com.example.myFirstProject.ServiceImpl;

import com.example.myFirstProject.dto.InvoiceDetailDTO;
import com.example.myFirstProject.dto.InvoiceSummaryDTO;
import com.example.myFirstProject.exception.ResourceNotFoundException;
import com.example.myFirstProject.model.Invoice;
import com.example.myFirstProject.repository.InvoiceRepository;
import com.example.myFirstProject.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ModelMapper modelMapper) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InvoiceDetailDTO createInvoice(InvoiceDetailDTO invoiceDetailDTO) {
        Invoice invoice = modelMapper.map(invoiceDetailDTO, Invoice.class);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return modelMapper.map(savedInvoice, InvoiceDetailDTO.class);
    }

    @Override
    public InvoiceDetailDTO getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
        return modelMapper.map(invoice, InvoiceDetailDTO.class);
    }

    @Override
    public List<InvoiceSummaryDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
