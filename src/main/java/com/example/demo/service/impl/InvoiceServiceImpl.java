package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Invoice;
import com.example.demo.model.CategorizationRule;
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
    private List<CategorizationRule> rules; // Or fetch from repository

    @Override
    public Invoice categorizeInvoice(Invoice invoice) {
        // Use the engine to get category name
        String categoryName = engine.determineCategory(invoice, rules);

        // Convert category name to Category entity
        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    return categoryRepository.save(newCategory);
                });

        // Set the Category object to invoice
        invoice.setCategory(category);

        return invoiceRepository.save(invoice);
    }
}
