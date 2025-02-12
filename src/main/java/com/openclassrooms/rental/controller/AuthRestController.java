package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.request.AuthLoginRequest;
import com.openclassrooms.rental.dto.request.AuthRegisterRequest;
import com.openclassrooms.rental.dto.response.AuthResponse;
import com.openclassrooms.rental.dto.response.UserResponse;
import com.openclassrooms.rental.service.auth.AuthenticationService;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private final AuthenticationService authService;

    AuthRestController(AuthenticationService authService) {
        this.authService = authService;
    }

    @GetMapping("me")
    public ResponseEntity<UserResponse> getMe(Authentication authentication) {
        return ResponseEntity.ok(authService.getUserInfo(authentication));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest body) {
        return ResponseEntity.ok(authService.authenticate(body));
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRegisterRequest body) throws Exception {
        return ResponseEntity.ok(authService.register(body));
    }

}
