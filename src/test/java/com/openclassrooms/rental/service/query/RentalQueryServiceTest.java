package com.openclassrooms.rental.service.query;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.dto.response.RentalsResponse;
import com.openclassrooms.rental.model.RentalEntity;
import com.openclassrooms.rental.repository.RentalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RentalQueryServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalQueryService rentalQueryService;

    @Test
    void getRentalById_shouldReturnRental_whenRentalExists() {
        RentalEntity rentalEntity = buildRentalEntity();

        when(rentalRepository.findById(1)).thenReturn(Optional.of(rentalEntity));

        RentalResponse response = rentalQueryService.getRentalById(1);

        assertNotNull(response);
        assertEquals(rentalEntity.getId(), response.getId());
        assertEquals(rentalEntity.getName(), response.getName());
    }

    @Test
    void getRentalById_shouldThrowException_whenRentalDoesNotExist() {
        when(rentalRepository.findById(1)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            rentalQueryService.getRentalById(1);
        });
        assertEquals("Rental not found", exception.getMessage());
    }

    @Test
    void getRentals_shouldReturnAllRentals() {
        RentalEntity rentalEntity1 = buildRentalEntity();

        RentalEntity rentalEntity2 = buildRentalEntity();

        List<RentalEntity> rentalEntities = List.of(rentalEntity1, rentalEntity2);
        when(rentalRepository.findAll()).thenReturn(rentalEntities);

        RentalsResponse rentalsResponse = rentalQueryService.getRentals();

        assertNotNull(rentalsResponse);
        assertNotNull(rentalsResponse.getRentals());
        assertEquals(2, rentalsResponse.getRentals().size());
    }

    private RentalEntity buildRentalEntity() {
        LocalDateTime now = LocalDateTime.now();
        RentalEntity rentalEntity = RentalEntity.builder()
                .id(1)
                .name("Rental 1")
                .surface(100.0)
                .price(1500.0)
                .picture("http://example.com/pic1.jpg")
                .description("Description 1")
                .created_at(now)
                .updated_at(now)
                .owner_id(42)
                .build();
        return rentalEntity;
    }
}
