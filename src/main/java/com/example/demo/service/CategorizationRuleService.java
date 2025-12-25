package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // Rule name
    private String keyword;         // Example criteria

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;      // The category this rule assigns

    // Constructors
    public CategorizationRule() {}

    public CategorizationRule(String name, String keyword, Category category) {
        this.name = name;
        this.keyword = keyword;
        this.category = category;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // New: matches method for InvoiceCategorizationEngine
    public boolean matches(Invoice invoice) {
        if (invoice == null || invoice.getDescription() == null) {
            return false;
        }
        return invoice.getDescription().contains(this.keyword);
    }

    // Optional: equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategorizationRule)) return false;
        CategorizationRule that = (CategorizationRule) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
