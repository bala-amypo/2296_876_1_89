package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "invoices",
    uniqueConstraints = @UniqueConstraint(columnNames = {"vendor_id", "invoice_number"})
)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Vendor vendor;

    @Column(name = "invoice_number", nullable = false)
    private String invoiceNumber;

    private Double amount;

    private LocalDateTime invoiceDate;

    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne(optional = false)
    private User uploadedBy;

    private LocalDateTime uploadedAt;

    @PrePersist
    protected void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public Vendor getVendor() { return vendor; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public Double getAmount() { return amount; }
    public LocalDateTime getInvoiceDate() { return invoiceDate; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
    public User getUploadedBy() { return uploadedBy; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }

    public void setId(Long id) { this.id = id; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setInvoiceDate(LocalDateTime invoiceDate) { this.invoiceDate = invoiceDate; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(Category category) { this.category = category; }
    public void setUploadedBy(User uploadedBy) { this.uploadedBy = uploadedBy; }
}
