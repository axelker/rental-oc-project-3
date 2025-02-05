package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.request.AuthLoginDtoRequest;
import com.openclassrooms.rental.dto.request.AuthRegisterDtoRequest;
import com.openclassrooms.rental.dto.response.AuthDtoResponse;
import com.openclassrooms.rental.dto.response.UserDtoResponse;

import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/auth")
public class AuthRestController {
    

    @GetMapping("me")
    public ResponseEntity<UserDtoResponse> getMe() {
        final var user =  new UserDtoResponse(1L, "Owner Name", "test@test.com",
                LocalDate.of(2022, 2, 2),
                LocalDate.of(2022, 8, 2));

        return ResponseEntity.ok(user);
    }
    
    @PostMapping("login")
    public ResponseEntity<AuthDtoResponse> login(@RequestBody AuthLoginDtoRequest body) {
        final var auth =  new AuthDtoResponse("jwt");
        return ResponseEntity.ok(auth);
    }

    @PostMapping("register")
    public ResponseEntity<AuthDtoResponse> register(@RequestBody AuthRegisterDtoRequest body) {
        final var auth =  new AuthDtoResponse("jwt");
        return ResponseEntity.ok(auth);
    }
    
    
}
