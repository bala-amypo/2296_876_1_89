package com.example.demo.service.impl;

import com.example.demo.entity.Invoice;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.InvoiceService;
import com.example.demo.util.InvoiceCategorizationEngine;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final VendorRepository vendorRepository;
    private final CategoryRepository categoryRepository;
    private final InvoiceCategorizationEngine categorizationEngine;

    public InvoiceServiceImpl(
            InvoiceRepository invoiceRepository,
            VendorRepository vendorRepository,
            CategoryRepository categoryRepository,
            InvoiceCategorizationEngine categorizationEngine
    ) {
        this.invoiceRepository = invoiceRepository;
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
        this.categorizationEngine = categorizationEngine;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        // Auto category logic
        String categoryName = categorizationEngine.categorize(invoice.getDescription());
        invoice.setCategoryName(categoryName);
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
}
