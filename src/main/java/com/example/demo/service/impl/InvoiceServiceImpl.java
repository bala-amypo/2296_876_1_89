 package com.example.demo.service.impl;

import com.example.demo.model.Invoice;
import com.example.demo.repository.*;
import com.example.demo.service.InvoiceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepo;
    private final UserRepository userRepo;
    private final VendorRepository vendorRepo;
    private final CategorizationRuleRepository ruleRepo;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepo,
                              UserRepository userRepo,
                              VendorRepository vendorRepo,
                              CategorizationRuleRepository ruleRepo) {
        this.invoiceRepo = invoiceRepo;
        this.userRepo = userRepo;
        this.vendorRepo = vendorRepo;
        this.ruleRepo = ruleRepo;
    }

    public Invoice uploadInvoice(Long userId, Long vendorId, Invoice invoice) {
        invoice.setUser(userRepo.findById(userId).orElseThrow());
        invoice.setVendor(vendorRepo.findById(vendorId).orElseThrow());

        if (invoice.getAmount() <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");

        return invoiceRepo.save(invoice);
    }

    public Invoice categorizeInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepo.findById(invoiceId).orElseThrow();
        // apply categorization engine here
        return invoiceRepo.save(invoice);
    }

    public List<Invoice> getInvoicesByUser(Long userId) {
        return invoiceRepo.findByUserId(userId);
    }

    public Invoice getInvoice(Long id) {
        return invoiceRepo.findById(id).orElseThrow();
    }
}
