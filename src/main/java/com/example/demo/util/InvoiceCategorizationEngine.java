package com.example.demo.util;

import com.example.demo.entity.Category;
import com.example.demo.entity.CategorizationRule;
import com.example.demo.entity.Invoice;

import java.util.List;
import java.util.Locale;

public final class InvoiceCategorizationEngine {

    private InvoiceCategorizationEngine() {
        // Utility class – prevent instantiation
    }

    /**
     * Applies categorization rules to an invoice
     */
    public static Category categorizeInvoice(
            Invoice invoice,
            List<CategorizationRule> rules
    ) {

        if (invoice == null || rules == null || rules.isEmpty()) {
            return null;
        }

        String vendorName = safeLower(
                invoice.getVendor() != null
                        ? invoice.getVendor().getVendorName()
                        : null
        );

        String description = safeLower(invoice.getDescription());

        for (CategorizationRule rule : rules) {

            if (!rule.isActive()) {
                continue;
            }

            boolean vendorMatch = matches(
                    vendorName,
                    rule.getVendorKeyword()
            );

            boolean descriptionMatch = matches(
                    description,
                    rule.getDescriptionKeyword()
            );

            if (vendorMatch || descriptionMatch) {
                return rule.getCategory();
            }
        }

        return null; // No rule matched
    }

    // ================= HELPER METHODS =================

    private static boolean matches(String source, String keyword) {
        if (source == null || keyword == null || keyword.isBlank()) {
            return false;
        }
        return source.contains(
                keyword.toLowerCase(Locale.ROOT)
        );
    }

    private static String safeLower(String value) {
        return value == null
                ? null
                : value.toLowerCase(Locale.ROOT);
    }
}
