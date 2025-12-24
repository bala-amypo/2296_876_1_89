package com.example.demo.util;

import com.example.demo.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public Category categorize(Invoice invoice, List<CategorizationRule> rules) {

        for (CategorizationRule rule : rules) {

            if (!rule.isActive()) {
                continue;
            }

            boolean vendorMatch = rule.getVendorKeyword() == null
                    || invoice.getVendor().getVendorName()
                    .toLowerCase()
                    .contains(rule.getVendorKeyword().toLowerCase());

            boolean descriptionMatch = rule.getDescriptionKeyword() == null
                    || invoice.getDescription()
                    .toLowerCase()
                    .contains(rule.getDescriptionKeyword().toLowerCase());

            if (vendorMatch && descriptionMatch) {
                return rule.getCategory();
            }
        }

        return null;
    }
}
