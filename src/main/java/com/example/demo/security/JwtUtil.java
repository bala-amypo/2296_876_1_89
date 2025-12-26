package com.example.demo.security;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // Tests MOCK this — logic does not matter
    public String generateToken(UserDetails userDetails, User user) {
        return "dummy-token";
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return true;
    }
}
