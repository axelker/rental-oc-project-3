package com.openclassrooms.rental.service.query;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.dto.response.RentalsResponse;
import com.openclassrooms.rental.mapper.RentalMapper;
import com.openclassrooms.rental.model.RentalEntity;
import com.openclassrooms.rental.repository.RentalRepository;

@Service
public class RentalQueryService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalQueryService(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    public RentalResponse getRentalById(Integer id) throws NoSuchElementException {
        RentalEntity rental = rentalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rental not found"));
        return rentalMapper.toDto(rental);
    }

    public RentalsResponse getRentals() {
        return RentalsResponse.builder()
                .rentals(
                        StreamSupport.stream(rentalRepository.findAll().spliterator(), false)
                                .map(rentalMapper::toDto)
                                .collect(Collectors.toList()))
                .build();
    }

}
