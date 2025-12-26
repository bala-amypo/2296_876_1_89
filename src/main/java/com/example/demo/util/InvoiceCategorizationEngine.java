package com.example.demo.util;

import com.example.demo.model.*;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class InvoiceCategorizationEngine {

    public Category determineCategory(Invoice invoice, List<CategorizationRule> rules) {
        return rules.stream()
                .sorted(Comparator.comparingInt(CategorizationRule::getPriority).reversed())
                .filter(r -> matches(invoice.getDescription(), r))
                .map(r -> r.getCategory())
                .findFirst()
                .orElse(null);
    }

    private boolean matches(String desc, CategorizationRule rule) {
        if (desc == null) return false;

        return switch (rule.getMatchType()) {
            case "EXACT" -> desc.equals(rule.getKeyword());
            case "CONTAINS" -> desc.toLowerCase().contains(rule.getKeyword().toLowerCase());
            case "REGEX" -> Pattern.matches(rule.getKeyword(), desc);
            default -> false;
        };
    }
}
