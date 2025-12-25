package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    private List<CategorizationRule> rules;

    public InvoiceCategorizationEngine() {
        // Initialize rules here or inject via constructor
    }

    public String determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        for (CategorizationRule rule : rules) {
            if (rule.matches(invoice)) {
                return rule.getCategoryName();
            }
        }
        return "Uncategorized";
    }

    public List<CategorizationRule> getAllRules() {
        return rules;
    }

    public void setRules(List<CategorizationRule> rules) {
        this.rules = rules;
    }
}
