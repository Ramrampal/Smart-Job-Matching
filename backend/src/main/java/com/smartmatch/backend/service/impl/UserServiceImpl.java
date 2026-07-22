package com.smartmatch.backend.service.impl;

import com.smartmatch.backend.dto.LoginResponse;
import com.smartmatch.backend.entity.User;
import com.smartmatch.backend.exception.EmailAlreadyExistsException;
import com.smartmatch.backend.repository.UserRepository;
import com.smartmatch.backend.service.UserService;
import com.smartmatch.backend.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public LoginResponse login(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return new LoginResponse(
                    "Email not found",
                    false,
                    null,
                    null,
                    null
            );
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new LoginResponse(
                    "Invalid password",
                    false,
                    null,
                    null,
                    null
            );
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(
                "Login Successful",
                true,
                token,
                user.getId(),
                user.getRole()
        );
    }
}