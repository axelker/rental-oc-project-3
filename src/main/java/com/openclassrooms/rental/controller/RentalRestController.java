package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.dto.response.RentalsResponse;
import com.openclassrooms.rental.dto.response.Response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/rentals")
public class RentalRestController {
    
    @GetMapping("")
    public ResponseEntity<RentalsResponse> getRentals() {
       return ResponseEntity.ok(new RentalsResponse(List.of(
            new RentalResponse(1, "test house 1", 432, 300, 
                "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                1, LocalDateTime.of(2012, 12, 2, 0, 0), LocalDateTime.of(2014, 12, 2, 0, 0)),

            new RentalResponse(2, "test house 2", 154, 200, 
                "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                2, LocalDateTime.of(2012, 12, 2, 0, 0), LocalDateTime.of(2014, 12, 2, 0, 0)),

            new RentalResponse(3, "test house 3", 234, 100, 
                "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                1, LocalDateTime.of(2012, 12, 2, 0, 0), LocalDateTime.of(2014, 12, 2, 0, 0))
        )));
    }

    @GetMapping("{id}")
    public ResponseEntity<RentalResponse> getRental(@PathVariable Integer id) {
        return ResponseEntity.ok(new RentalResponse(1, "test house 1", 432, 300, 
                "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                1, LocalDateTime.of(2012, 12, 2, 0, 0), LocalDateTime.of(2014, 12, 2, 0, 0)));
    }

    @PostMapping("")
    public ResponseEntity<Response> createRental(
            @RequestParam String name,
            @RequestParam double surface,
            @RequestParam double price,
            @RequestParam String description,
            @RequestParam MultipartFile picture) {  
       return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Rental created ! "));
    }

    @PutMapping("{id}")
    public ResponseEntity<Response> updateRental(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam double surface,
            @RequestParam double price,
            @RequestParam String description) {        
        return ResponseEntity.ok().body(new Response("Rental updated !"));
    }
    
    
}
