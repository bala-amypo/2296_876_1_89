package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categorization_rules")
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleName;

    @Column(nullable = false)
    private String conditionField; // example: amount, vendorName

    @Column(nullable = false)
    private String operator; // GREATER_THAN, CONTAINS, EQUALS

    @Column(nullable = false)
    private String conditionValue;

    @Column(nullable = false)
    private String categoryName;

    // Constructors
    public CategorizationRule() {}

    public CategorizationRule(String ruleName, String conditionField, String operator,
                              String conditionValue, String categoryName) {
        this.ruleName = ruleName;
        this.conditionField = conditionField;
        this.operator = operator;
        this.conditionValue = conditionValue;
        this.categoryName = categoryName;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getConditionField() { return conditionField; }
    public void setConditionField(String conditionField) { this.conditionField = conditionField; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public String getConditionValue() { return conditionValue; }
    public void setConditionValue(String conditionValue) { this.conditionValue = conditionValue; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
