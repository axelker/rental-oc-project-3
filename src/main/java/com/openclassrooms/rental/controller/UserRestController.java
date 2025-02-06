package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.response.UserResponse;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/user")
public class UserRestController {
    

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer id) {
        final var user =  new UserResponse(1, "Owner Name", "test@test.com",
                LocalDateTime.of(2022, 2, 2, 0, 0),
                LocalDateTime.of(2022, 8, 2, 0, 0));

        return ResponseEntity.ok(user);
    }
    
}
