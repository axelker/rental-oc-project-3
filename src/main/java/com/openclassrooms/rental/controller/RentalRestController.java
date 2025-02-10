package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.dto.response.RentalsResponse;
import com.openclassrooms.rental.dto.response.Response;
import com.openclassrooms.rental.service.command.RentalCommandService;
import com.openclassrooms.rental.service.query.RentalQueryService;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/rentals")
public class RentalRestController {

    private final RentalQueryService rentalQueryService;
    private final RentalCommandService rentalCommandService;

    public RentalRestController(RentalQueryService rentalQueryService, RentalCommandService rentalCommandService) {
        this.rentalQueryService = rentalQueryService;
        this.rentalCommandService = rentalCommandService;
    }

    @GetMapping("")
    public ResponseEntity<RentalsResponse> getRentals() {
        return ResponseEntity.ok(rentalQueryService.getRentals());
    }

    @GetMapping("{id}")
    public ResponseEntity<RentalResponse> getRental(@PathVariable Integer id) {
        return ResponseEntity.ok(rentalQueryService.getRentalById(id));
    }

    @PostMapping("")
    public ResponseEntity<Response> createRental(
            @RequestParam String name,
            @RequestParam double surface,
            @RequestParam double price,
            @RequestParam String description,
            @RequestParam MultipartFile picture) {
        try {
            // todo: build custom object RequestRental with optional picture and include
            // user_id of JWT.
            rentalCommandService.createRental(name, surface, price, description, picture);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(new Response("Error to save rental image."));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Rental created ! "));
    }

    @PutMapping("{id}")
    public ResponseEntity<Response> updateRental(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam double surface,
            @RequestParam double price,
            @RequestParam String description) {
        // todo: build custom object RequestRental with optional picture and include
        // user_id of JWT.
        rentalCommandService.updateRental(id, name, surface, price, description);
        return ResponseEntity.ok().body(new Response("Rental updated !"));
    }

}
