package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {

    User registerUser(User user);      // Add this
    List<User> getAllUsers();          // Add this

    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
