package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.UserDto;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/user")
public class UserRestController {
    

    @GetMapping("{id}")
    public UserDto getMethodName(@PathVariable Long id) {
        return new UserDto(2L, "Owner Name", "test@test.com",
                LocalDate.of(2022, 2, 2),
                LocalDate.of(2022, 8, 2));   
    }
    
}
