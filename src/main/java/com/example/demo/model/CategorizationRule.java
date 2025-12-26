package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    // IMPORTANT: name expected by services
    private String categoryName;

    public CategorizationRule() {
    }

    public CategorizationRule(String keyword, String categoryName) {
        this.keyword = keyword;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Used by InvoiceCategorizationEngine
    public boolean matches(Invoice invoice) {
        if (invoice == null || invoice.getDescription() == null || keyword == null) {
            return false;
        }
        return invoice.getDescription()
                .toLowerCase()
                .contains(keyword.toLowerCase());
    }
}
