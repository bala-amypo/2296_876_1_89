package com.example.demo.controller;

import com.example.demo.model.Invoice;
import com.example.demo.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping("/upload/{userId}/{vendorId}")
    public Invoice upload(@PathVariable Long userId,
                          @PathVariable Long vendorId,
                          @RequestBody Invoice invoice) {
        return service.uploadInvoice(userId, vendorId, invoice);
    }

    @PostMapping("/categorize/{invoiceId}")
    public Invoice categorize(@PathVariable Long invoiceId) {
        return service.categorizeInvoice(invoiceId);
    }
}
