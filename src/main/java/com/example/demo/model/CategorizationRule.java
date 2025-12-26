package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    // IMPORTANT: name expected by services
    @Column(nullable = false)
    private String categoryName;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public CategorizationRule() {
    }

    public CategorizationRule(String keyword, String categoryName) {
        this.keyword = keyword;
        this.categoryName = categoryName;
    }

    /* ---------- Lifecycle ---------- */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    /* ---------- Getters & Setters ---------- */
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /* ---------- Used by InvoiceCategorizationEngine ---------- */
    public boolean matches(Invoice invoice) {
        if (invoice == null || invoice.getDescription() == null || keyword == null) {
            return false;
        }
        return invoice.getDescription()
                .toLowerCase()
                .contains(keyword.toLowerCase());
    }
}
