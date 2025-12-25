package com.example.demo.util;

import com.example.demo.entity.CategorizationRule;
import com.example.demo.entity.Category;
import com.example.demo.entity.Invoice;

import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public Category determineCategory(
            Invoice invoice,
            List<CategorizationRule> rules) {

        if (rules == null || rules.isEmpty()) {
            return null;
        }

        return rules.stream()
                .sorted((a, b) -> Integer.compare(b.getPriority(), a.getPriority()))
                .filter(rule -> matches(rule, invoice.getDescription()))
                .map(CategorizationRule::getCategory)
                .findFirst()
                .orElse(null);
    }

    private boolean matches(CategorizationRule rule, String description) {

        if (description == null || rule.getKeyword() == null) {
            return false;
        }

        return switch (rule.getMatchType()) {
            case EXACT -> description.equals(rule.getKeyword());
            case CONTAINS ->
                    description.toLowerCase()
                            .contains(rule.getKeyword().toLowerCase());
            case REGEX ->
                    Pattern.compile(rule.getKeyword())
                            .matcher(description)
                            .find();
        };
    }
}
