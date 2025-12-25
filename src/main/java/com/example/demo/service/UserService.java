package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
