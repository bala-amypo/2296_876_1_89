package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;        // e.g., text to match in invoice description
    private String categoryName;   // Name of the category to assign

    // Constructors
    public CategorizationRule() {}

    public CategorizationRule(String keyword, String categoryName) {
        this.keyword = keyword;
        this.categoryName = categoryName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
     * Checks if the rule matches the given invoice
     */
    public boolean matches(Invoice invoice) {
        if (invoice.getDescription() == null) return false;
        return invoice.getDescription().toLowerCase().contains(keyword.toLowerCase());
    }
}
