package com.openclassrooms.rental.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.response.ErrorResponse;
import com.openclassrooms.rental.service.storage.ImageStorageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Storage", description = "Endpoints for retrieving stored files.")
@ApiResponses({
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
})
@RestController
@RequestMapping("/storage")
public class StorageRestController {
    private final ImageStorageService imageStorageService;

    StorageRestController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @Operation(summary = "Retrieve an image", description = "Fetches an image from storage by filename.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image retrieved successfully", content = @Content(mediaType = MediaType.IMAGE_PNG_VALUE)),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/images/{fileName}", produces = { MediaType.IMAGE_PNG_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getImage(@PathVariable String fileName) throws IOException {
        byte[] image = imageStorageService.loadImage(fileName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

}