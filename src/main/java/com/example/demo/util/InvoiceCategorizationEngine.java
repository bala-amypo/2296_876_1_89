package com.example.demo.util;

import com.example.demo.model.Invoice;
import com.example.demo.model.Category;
import com.example.demo.model.CategorizationRule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public Category determineCategory(
            Invoice invoice,
            List<CategorizationRule> rules
    ) {
        // BASIC DEFAULT LOGIC (safe for now)

        if (rules == null || rules.isEmpty()) {
            return null;
        }

        for (CategorizationRule rule : rules) {
            // You can add real matching logic later
            return rule.getCategory();
        }

        return null;
    }
}
