package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.dto.response.RentalsResponse;
import com.openclassrooms.rental.dto.response.Response;
import com.openclassrooms.rental.service.auth.JWTService;
import com.openclassrooms.rental.service.command.RentalCommandService;
import com.openclassrooms.rental.service.query.RentalQueryService;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/rentals")
public class RentalRestController {

    private final RentalQueryService rentalQueryService;
    private final RentalCommandService rentalCommandService;
    private final JWTService jwtService;

    public RentalRestController(RentalQueryService rentalQueryService, RentalCommandService rentalCommandService,
            JWTService jwtService) {
        this.rentalQueryService = rentalQueryService;
        this.rentalCommandService = rentalCommandService;
        this.jwtService = jwtService;
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
            @RequestParam MultipartFile picture,
            Authentication authentication) {

        try {

            rentalCommandService.createRental(name, surface, price, description, picture,
                    jwtService.getUserId(authentication));
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(Response.builder().message("Error to save rental image.").build());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.builder().message("Rental created ! ").build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Response> updateRental(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam double surface,
            @RequestParam double price,
            @RequestParam String description) {
        rentalCommandService.updateRental(id, name, surface, price, description);
        return ResponseEntity.ok().body(Response.builder().message("Rental updated !").build());
    }

}
