package com.openclassrooms.rental.controller;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.service.storage.ImageStorageService;

@RestController
@RequestMapping("/storage")
public class StorageRestController {
    private final ImageStorageService imageStorageService;

    StorageRestController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @GetMapping("/images/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) throws IOException {
        byte[] image = imageStorageService.loadImage(fileName);
        return ResponseEntity.ok(image);
    }
}