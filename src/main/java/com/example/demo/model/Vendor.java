package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String vendorName;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Vendor() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getVendorName() { return vendorName; }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
