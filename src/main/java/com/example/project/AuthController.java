package com.example.project;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .setDisplayName(user.getName());

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return "User registered successfully with ID: " + userRecord.getUid();
        } catch (FirebaseAuthException e) {
            return "Error registering user: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        // Nota: La verificación real del inicio de sesión debe usar un cliente de Firebase Auth
        // o un sistema de generación de tokens JWT.
        return "Login successful for email: " + loginRequest.getEmail();
    }
}
