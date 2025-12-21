package com.example.demo.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
        name = "user_favorite_vendors",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "vendor_id")
    )
    @JsonIgnore
    private Set<Vendor> favoriteVendors = new HashSet<>();

    // -------- Constructors --------
    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // -------- Getters & Setters --------
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Vendor> getFavoriteVendors() {
        return favoriteVendors;
    }

    public void setFavoriteVendors(Set<Vendor> favoriteVendors) {
        this.favoriteVendors = favoriteVendors;
    }
}
