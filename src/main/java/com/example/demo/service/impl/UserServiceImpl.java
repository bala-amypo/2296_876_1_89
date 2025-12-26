package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

public class UserServiceImpl {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User registerUser(User user) {
        if (repo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public User findByEmail(String email) {
        User u = repo.findByEmail(email);
        if (u == null) throw new ResourceNotFoundException("User not found");
        return u;
    }

    public User getUserById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
