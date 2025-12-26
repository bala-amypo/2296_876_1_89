package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private String category;

    public CategorizationRule() {
    }

    public CategorizationRule(String keyword, String category) {
        this.keyword = keyword;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // REQUIRED by InvoiceCategorizationEngine
    public boolean matches(Invoice invoice) {
        if (invoice == null || invoice.getDescription() == null || keyword == null) {
            return false;
        }
        return invoice.getDescription()
                .toLowerCase()
                .contains(keyword.toLowerCase());
    }
}
