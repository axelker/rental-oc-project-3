package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.response.UserResponse;
import com.openclassrooms.rental.service.query.UserQueryService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/user")
public class UserRestController {
    
    private final UserQueryService userQueryService;

    public UserRestController(UserQueryService userQueryService){
        this.userQueryService = userQueryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userQueryService.getUserById(id));
    }
    
}
