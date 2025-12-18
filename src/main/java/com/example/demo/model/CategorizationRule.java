 package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RuleType type;

    private String pattern;
    private int priority;

    @ManyToOne
    private Category category;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public RuleType getType() { return type; }
    public void setType(RuleType type) { this.type = type; }

    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
