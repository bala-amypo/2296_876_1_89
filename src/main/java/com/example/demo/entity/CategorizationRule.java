package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorKeyword;

    private String descriptionKeyword;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // ===== GETTERS =====
    public boolean isActive() {
        return active;
    }

    public String getVendorKeyword() {
        return vendorKeyword;
    }

    public String getDescriptionKeyword() {
        return descriptionKeyword;
    }

    public Category getCategory() {
        return category;
    }

    // ===== SETTERS =====
    public void setVendorKeyword(String vendorKeyword) {
        this.vendorKeyword = vendorKeyword;
    }

    public void setDescriptionKeyword(String descriptionKeyword) {
        this.descriptionKeyword = descriptionKeyword;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
