package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    public String determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        for (CategorizationRule rule : rules) {
            if (rule.matches(invoice)) {
                return rule.getCategory();
            }
        }
        return "Uncategorized";
    }
}
