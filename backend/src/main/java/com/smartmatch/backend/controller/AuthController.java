package com.smartmatch.backend.controller;

import com.smartmatch.backend.dto.LoginRequest;
import com.smartmatch.backend.dto.LoginResponse;
import com.smartmatch.backend.dto.RegisterRequest;
import com.smartmatch.backend.entity.User;
import com.smartmatch.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        userService.registerUser(user);

        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        LoginResponse response =
                userService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(response);
    }
}