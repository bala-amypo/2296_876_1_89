package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // ===== GETTERS =====
    public String getDescription() {
        return description;
    }

    public Vendor getVendor() {
        return vendor;
    }

    // ===== SETTERS =====
    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
