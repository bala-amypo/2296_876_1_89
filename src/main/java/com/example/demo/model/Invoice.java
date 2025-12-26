package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "invoices",
    uniqueConstraints = @UniqueConstraint(columnNames = {"vendor_id", "invoiceNumber"})
)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private Double amount;
    private LocalDate invoiceDate;
    private String description;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User uploadedBy;

    private LocalDateTime uploadedAt;

    @PrePersist
    public void prePersist() {
        uploadedAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public User getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(User uploadedBy) { this.uploadedBy = uploadedBy; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
}
