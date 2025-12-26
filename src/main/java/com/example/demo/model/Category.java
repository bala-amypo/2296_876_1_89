package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Internal storage
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Category() {}

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

    // Primary accessor
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 🔴 ALIAS REQUIRED BY TESTS
    public String getCategoryName() {
        return name;
    }

    // 🔴 ALIAS REQUIRED BY TESTS
    public void setCategoryName(String categoryName) {
        this.name = categoryName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 🔴 REQUIRED BY TESTS
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
