package com.example.demo.util;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Invoice;
import com.example.demo.repository.CategorizationRuleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceCategorizationEngine {

    private final CategorizationRuleRepository ruleRepository;

    public InvoiceCategorizationEngine(CategorizationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    // REQUIRED by InvoiceServiceImpl
    public List<CategorizationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    // REQUIRED by InvoiceServiceImpl
    public String determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        for (CategorizationRule rule : rules) {
            if (rule.matches(invoice)) {
                return rule.getCategoryName();
            }
        }
        return "Uncategorized";
    }
}
