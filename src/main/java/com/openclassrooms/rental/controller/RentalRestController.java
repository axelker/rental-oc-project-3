package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.rental.dto.response.RentalDtoResponse;
import com.openclassrooms.rental.dto.response.RentalsDtoResponse;
import com.openclassrooms.rental.dto.response.ResponseDto;

import java.time.LocalDate;
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
    public ResponseEntity<RentalsDtoResponse> getRentals() {
       return ResponseEntity.ok(new RentalsDtoResponse(List.of(
            new RentalDtoResponse(1L, "test house 1", 432, 300, 
                "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                1L, LocalDate.of(2012, 12, 2), LocalDate.of(2014, 12, 2)),

            new RentalDtoResponse(2L, "test house 2", 154, 200, 
                "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                2L, LocalDate.of(2012, 12, 2), LocalDate.of(2014, 12, 2)),

            new RentalDtoResponse(3L, "test house 3", 234, 100, 
                "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                1L, LocalDate.of(2012, 12, 2), LocalDate.of(2014, 12, 2))
        )));
    }

    @GetMapping("{id}")
    public ResponseEntity<RentalDtoResponse> getRental(@PathVariable Long id) {
        return ResponseEntity.ok(new RentalDtoResponse(1L, "test house 1", 432, 300, 
                "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                1L, LocalDate.of(2012, 12, 2), LocalDate.of(2014, 12, 2)));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createRental(
            @RequestParam String name,
            @RequestParam Integer surface,
            @RequestParam Integer price,
            @RequestParam  String description,
            @RequestParam MultipartFile picture) {  
       return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Rental created ! "));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updateRental(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam Integer surface,
            @RequestParam Integer price,
            @RequestParam String description) {        
        return ResponseEntity.ok().body(new ResponseDto("Rental updated !"));
    }
    
    
}
