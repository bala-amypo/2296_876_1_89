package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class InvoiceCategorizationEngine {

    public String categorize(String description) {
        if (description == null) {
            return "UNCATEGORIZED";
        }

        String text = description.toLowerCase();

        if (text.contains("fuel") || text.contains("petrol") || text.contains("diesel")) {
            return "FUEL";
        }
        if (text.contains("hotel") || text.contains("stay")) {
            return "ACCOMMODATION";
        }
        if (text.contains("food") || text.contains("restaurant")) {
            return "FOOD";
        }

        return "OTHERS";
    }
}
