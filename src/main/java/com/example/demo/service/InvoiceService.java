package com.example.demo.service;

import com.example.demo.model.Invoice;

public interface InvoiceService {
    Invoice categorizeInvoice(Invoice invoice);
    Invoice getInvoice(Long id); // Implement this in Impl
}
