package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private String category;

    public CategorizationRule() {}

    public CategorizationRule(String name, String keyword, String category) {
        this.name = name;
        this.keyword = keyword;
        this.category = category;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    // Utility method for matching invoice
    public boolean matches(Invoice invoice) {
        return invoice.getDescription() != null && invoice.getDescription().contains(keyword);
    }
}
