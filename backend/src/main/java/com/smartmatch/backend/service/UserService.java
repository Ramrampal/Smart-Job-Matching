package com.smartmatch.backend.service;

import com.smartmatch.backend.dto.LoginResponse;
import com.smartmatch.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    LoginResponse login(String email, String password);
}