package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
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
    private VendorRepository vendorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private InvoiceCategorizationEngine engine;

    @Override
    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        invoice.setUser(userRepository.findById(userId).orElse(null));
        invoice.setVendor(vendorRepository.findById(vendorId).orElse(null));
        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public List<Invoice> getInvoicesByUser(Long userId) {
        return invoiceRepository.findByUserId(userId);
    }

    @Override
    public Invoice categorizeInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElse(null);
        if (invoice == null) return null;

        List<CategorizationRule> rules = engine.getAllRules(); // make sure this returns rules, not categories
        Category category = engine.determineCategory(invoice, rules); // returns Category
        invoice.setCategory(category);
        invoiceRepository.save(invoice);
        return invoice;
    }
}
