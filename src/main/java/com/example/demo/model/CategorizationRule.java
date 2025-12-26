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

    // Used by services & tests
    @Column(nullable = false)
    private String categoryName;

    // 🔴 REQUIRED BY TESTS
    private String matchType;

    // 🔴 REQUIRED BY TESTS
    private int priority;

    // 🔴 REQUIRED BY TESTS
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public CategorizationRule() {}

    public CategorizationRule(String keyword, String categoryName) {
        this.keyword = keyword;
        this.categoryName = categoryName;
    }

    /* ---------- Lifecycle ---------- */
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    /* ---------- Getters & Setters ---------- */

    public Long getId() {
        return id;
    }

    // 🔴 REQUIRED BY TESTS
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

    // 🔴 REQUIRED BY TESTS
    public String getMatchType() {
        return matchType;
    }

    // 🔴 REQUIRED BY TESTS
    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    // 🔴 REQUIRED BY TESTS
    public int getPriority() {
        return priority;
    }

    // 🔴 REQUIRED BY TESTS
    public void setPriority(int priority) {
        this.priority = priority;
    }

    // 🔴 REQUIRED BY TESTS
    public Category getCategory() {
        return category;
    }

    // 🔴 REQUIRED BY TESTS
    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 🔴 REQUIRED BY TESTS
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
