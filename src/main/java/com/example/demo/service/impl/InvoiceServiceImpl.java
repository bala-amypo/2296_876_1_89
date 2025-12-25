package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.model.Category;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.InvoiceService;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private InvoiceCategorizationEngine engine;

    @Override
    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        invoice.setUserId(userId);
        invoice.setVendorId(vendorId);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoice(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).orElse(null);
    }

    @Override
    public List<Invoice> getInvoicesByUser(Long userId) {
        return invoiceRepository.findByUserId(userId);
    }

    @Override
    public Invoice categorizeInvoice(Long invoiceId) {
        Invoice invoice = getInvoice(invoiceId);
        if (invoice == null) return null;

        // Get all categorization rules
        List<com.example.demo.model.CategorizationRule> rules = engine.getAllRules();

        // Determine category name using engine
        String categoryName = engine.determineCategory(invoice, rules);

        // Convert to Category object
        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCat = new Category();
                    newCat.setName(categoryName);
                    return categoryRepository.save(newCat);
                });

        invoice.setCategory(category);

        return invoiceRepository.save(invoice);
    }
}
