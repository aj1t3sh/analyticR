package com.ps05.analyticsbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.ps05.analyticsbackend.dto.LoginRequest;
import com.ps05.analyticsbackend.security.JwtUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        System.out.println("USERNAME = " + username);
        System.out.println("PASSWORD = " + password);

        Map<String, String> response = new HashMap<>();

        // ADMIN LOGIN

        if(username.equals("admin") && password.equals("admin123")) {

            String token = jwtUtil.generateToken(username);

            response.put("token", token);
            response.put("role", "admin");

            return response;
        }

        // USER LOGIN

        else if(username.equals("user") && password.equals("user123")) {

            String token = jwtUtil.generateToken(username);

            response.put("token", token);
            response.put("role", "user");

            return response;
        }

        throw new RuntimeException("Invalid Credentials");
    }
}