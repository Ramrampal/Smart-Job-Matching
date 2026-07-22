package com.smartmatch.backend.dto;

public class LoginResponse {

    private String message;
    private boolean success;
    private String token;
    private Long userId;
    private String role;

    public LoginResponse() {
    }

    public LoginResponse(String message, boolean success, String token, Long userId, String role) {
        this.message = message;
        this.success = success;
        this.token = token;
        this.userId = userId;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}