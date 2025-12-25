package com.example.demo.util;

import com.example.demo.model.Invoice;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        for (CategorizationRule rule : rules) {
            if (rule.matches(invoice)) {
                return rule.getCategory();
            }
        }
        return null; // or default category if you have one
    }
}
