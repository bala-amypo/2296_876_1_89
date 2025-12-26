package com.example.demo.util;

import com.example.demo.model.*;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        if (rules == null || rules.isEmpty()) return null;

        return rules.stream()
                .sorted(Comparator.comparingInt(CategorizationRule::getPriority).reversed())
                .filter(rule -> matches(invoice.getDescription(), rule))
                .map(CategorizationRule::getCategory)
                .findFirst()
                .orElse(null);
    }

    private boolean matches(String desc, CategorizationRule rule) {
        if (desc == null) return false;

        switch (rule.getMatchType()) {
            case "EXACT":
                return desc.equals(rule.getKeyword());
            case "CONTAINS":
                return desc.toLowerCase().contains(rule.getKeyword().toLowerCase());
            case "REGEX":
                return Pattern.compile(rule.getKeyword()).matcher(desc).find();
            default:
                return false;
        }
    }
}
