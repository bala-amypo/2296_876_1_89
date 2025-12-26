package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.model.Category;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.CategoryRepository;
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

    @Override
    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        // userId & vendorId are no longer stored in Invoice entity
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoice(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).orElse(null);
    }

    @Override
    public List<Invoice> getInvoicesByUser(Long userId) {
        // Entity no longer supports userId
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice categorizeInvoice(Long invoiceId) {
        Invoice invoice = getInvoice(invoiceId);
        if (invoice == null) return null;

        var rules = engine.getAllRules();
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
}
