package com.openclassrooms.rental.service.query;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import com.openclassrooms.rental.model.RentalEntity;
import com.openclassrooms.rental.repository.RentalRepository;

@Service
public class RentalQueryService {
    private final RentalRepository rentalRepository;

    public RentalQueryService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public RentalEntity getRentalById(Integer id) throws NoSuchElementException {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rental not found"));
    }

    public List<RentalEntity> getRentals() {
        return rentalRepository.findAll();
    }

}
