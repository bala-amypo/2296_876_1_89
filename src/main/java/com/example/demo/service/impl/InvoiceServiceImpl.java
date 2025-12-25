package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Invoice;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.service.InvoiceService;
import com.example.demo.util.InvoiceCategorizationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private InvoiceCategorizationEngine engine;

    @Autowired
    private List<com.example.demo.model.CategorizationRule> rules;

    @Override
    public Invoice categorizeInvoice(Invoice invoice) {
        String categoryName = engine.determineCategory(invoice, rules);

        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    return categoryRepository.save(newCategory);
                });

        invoice.setCategory(category);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        // Assign userId and vendorId if your Invoice entity has these fields
        invoice.setUserId(userId);
        invoice.setVendorId(vendorId);

        // Categorize automatically
        return categorizeInvoice(invoice);
    }

    @Override
    public List<Invoice> getInvoicesByUser(Long userId) {
        return invoiceRepository.findByUserId(userId);
    }
}
