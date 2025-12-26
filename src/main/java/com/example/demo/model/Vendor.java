package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tests expect BOTH "name" and "vendorName" access
    @Column(name = "vendor_name", unique = true, nullable = false)
    private String vendorName;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Vendor() {}

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
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    // 🔴 ALIAS REQUIRED BY TESTS
    public String getName() {
        return vendorName;
    }

    // 🔴 ALIAS REQUIRED BY TESTS
    public void setName(String name) {
        this.vendorName = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 🔴 REQUIRED BY TESTS
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
