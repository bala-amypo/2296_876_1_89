package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;

import java.util.List;

public class InvoiceCategorizationEngine {

    public static String categorize(Invoice invoice, List<CategorizationRule> rules) {
        for (CategorizationRule rule : rules) {
            if (rule.matches(invoice)) {
                return rule.getCategory();
            }
        }
        return "UNCATEGORIZED";
    }
}
