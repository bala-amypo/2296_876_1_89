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
    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        // you can link user/vendor later
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice categorizeInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        String category = categorizationEngine.categorize(invoice.getDescription());
        invoice.setCategoryName(category);

        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getInvoicesByUser(Long userId) {
        // temporary: return all
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoice(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).orElse(null);
    }
}
