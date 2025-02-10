package com.openclassrooms.rental.service.query;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.dto.response.RentalsResponse;
import com.openclassrooms.rental.mapper.RentalMapper;
import com.openclassrooms.rental.model.RentalEntity;
import com.openclassrooms.rental.repository.RentalRepository;

@Service
public class RentalQueryService {
    private final RentalRepository rentalRepository;

    public RentalQueryService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public RentalResponse getRentalById(Integer id) throws NoSuchElementException {
        RentalEntity rental = rentalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rental not found"));
        return RentalMapper.toDto(rental);
    }

    public RentalsResponse getRentals() {
        return new RentalsResponse(rentalRepository.findAll()
                .stream()
                .map(RentalMapper::toDto)
                .collect(Collectors.toList()));
    }

}
