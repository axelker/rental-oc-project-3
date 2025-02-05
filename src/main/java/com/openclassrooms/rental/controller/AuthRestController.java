package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.request.AuthLoginRequest;
import com.openclassrooms.rental.dto.request.AuthRegisterRequest;
import com.openclassrooms.rental.dto.response.AuthResponse;
import com.openclassrooms.rental.dto.response.UserResponse;

import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/auth")
public class AuthRestController {
    

    @GetMapping("me")
    public ResponseEntity<UserResponse> getMe() {
        final var user =  new UserResponse(1L, "Owner Name", "test@test.com",
                LocalDate.of(2022, 2, 2),
                LocalDate.of(2022, 8, 2));

        return ResponseEntity.ok(user);
    }
    
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest body) {
        final var auth =  new AuthResponse("jwt");
        return ResponseEntity.ok(auth);
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRegisterRequest body) {
        final var auth =  new AuthResponse("jwt");
        return ResponseEntity.ok(auth);
    }
    
    
}
