package com.example.OnlineBookstoreManagementBackend.util;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    public boolean isValidPassword(String password) {
        // Password must be at least 8 characters long, contain at least one digit, one uppercase letter, and one special character
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        return password != null && password.matches(passwordRegex);
    }

    public boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.length() >= 3;
    }
}
