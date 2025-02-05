package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.AuthDto;
import com.openclassrooms.rental.dto.UserDto;

import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
public class AuthRestController {
    

    @GetMapping("me")
    public ResponseEntity<UserDto> getMe() {
        final var user =  new UserDto(2L, "Owner Name", "test@test.com",
                LocalDate.of(2022, 2, 2),
                LocalDate.of(2022, 8, 2));

        return ResponseEntity.ok(user);
    }
    
    @PostMapping("login")
    public ResponseEntity<AuthDto> login() {
        final var auth =  new AuthDto("jwt");
        return ResponseEntity.ok(auth);
    }

    @PostMapping("register")
    public ResponseEntity<AuthDto> register(@RequestBody String entity) {
        final var auth =  new AuthDto("jwt");
        return ResponseEntity.ok(auth);
    }
    
    
}
