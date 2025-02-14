package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.rental.dto.response.ErrorResponse;
import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.dto.response.RentalsResponse;
import com.openclassrooms.rental.dto.response.Response;
import com.openclassrooms.rental.service.auth.JWTService;
import com.openclassrooms.rental.service.command.RentalCommandService;
import com.openclassrooms.rental.service.query.RentalQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Rentals", description = "Manage rental properties")
@ApiResponses(value = {
                @ApiResponse(responseCode = "401", description = "Unauthorized - Token missing or invalid", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
})
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

        @Operation(summary = "Retrieve all rentals", description = "Fetch a list of all available rental.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "List of rentals successfully retrieved", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RentalsResponse.class))),
        })
        @GetMapping("")
        public ResponseEntity<RentalsResponse> getRentals() {
                return ResponseEntity.ok(rentalQueryService.getRentals());
        }

        @Operation(summary = "Find a rental by ID", description = "Retrieve details of a rental property using its ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Rental found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RentalResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
        })
        @GetMapping("{id}")
        public ResponseEntity<RentalResponse> getRental(@PathVariable Long id) {
                return ResponseEntity.ok(rentalQueryService.getRentalById(id));
        }

        @Operation(summary = "Create a new rental", description = "Add a new rental property to the system.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Rental created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))),
                        @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
        })
        @PostMapping("")
        public ResponseEntity<Response> createRental(
                        @RequestParam String name,
                        @RequestParam double surface,
                        @RequestParam double price,
                        @RequestParam String description,
                        @RequestParam MultipartFile picture,
                        Authentication authentication) throws IOException {

                rentalCommandService.createRental(name, surface, price, description, picture,
                                jwtService.getUserId(authentication));

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(Response.builder().message("Rental created successfully.").build());
        }

        @Operation(summary = "Update a rental", description = "Modify the details of an existing rental property.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Rental updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))),
                        @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
        })
        @PutMapping("{id}")
        public ResponseEntity<Response> updateRental(
                        @PathVariable Long id,
                        @RequestParam String name,
                        @RequestParam double surface,
                        @RequestParam double price,
                        @RequestParam String description) {
                rentalCommandService.updateRental(id, name, surface, price, description);
                return ResponseEntity.ok().body(Response.builder().message("Rental updated successfully.").build());
        }

}
