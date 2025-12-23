package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "vendors",
    uniqueConstraints = @UniqueConstraint(columnNames = "vendor_name")
)
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "vendor_name", nullable = false, unique = true)
    private String vendorName;

    @Column(nullable = false)
    private String contactEmail;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime createdAt;

    /**
     * Back-reference from User.favoriteVendors
     * Ignored to prevent infinite JSON recursion
     */
    @ManyToMany(mappedBy = "favoriteVendors")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @PrePersist
    
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Vendor() {}

    public Vendor(String vendorName, String contactEmail, String address) {
        this.vendorName = vendorName;
        this.contactEmail = contactEmail;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<User> getUsers() {
        return users;
    }
}
