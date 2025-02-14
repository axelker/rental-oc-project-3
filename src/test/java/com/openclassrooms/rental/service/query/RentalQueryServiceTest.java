package com.openclassrooms.rental.service.query;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.openclassrooms.rental.mapper.RentalMapper;

@ExtendWith(MockitoExtension.class)
public class RentalQueryServiceTest {

    @Mock
    private RentalMapper rentalMapper;
    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalQueryService rentalQueryService;

    @Test
    void getRentalById_shouldReturnRental_whenRentalExists() {
        RentalEntity rentalEntity = buildRentalEntity();
        RentalResponse rentalResponse = buildRentalResponse();

        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rentalEntity));
        when(rentalMapper.toDto(rentalEntity)).thenReturn(rentalResponse);

        RentalResponse response = rentalQueryService.getRentalById(1L);

        assertNotNull(response);
        assertEquals(rentalEntity.getId(), response.getId());
        assertEquals(rentalEntity.getName(), response.getName());
        verify(rentalMapper, times(1)).toDto(rentalEntity);

    }

    @Test
    void getRentalById_shouldThrowException_whenRentalDoesNotExist() {
        when(rentalRepository.findById(1l)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            rentalQueryService.getRentalById(1L);
        });
        assertEquals("Rental not found", exception.getMessage());
        verify(rentalMapper, never()).toDto(any());

    }

    @Test
    void getRentals_shouldReturnAllRentals() {
        RentalEntity rentalEntity1 = buildRentalEntity();
        RentalEntity rentalEntity2 = buildRentalEntity();

        RentalResponse rentalResponse1 = buildRentalResponse();
        RentalResponse rentalResponse2 = buildRentalResponse();

        List<RentalEntity> rentalEntities = List.of(rentalEntity1, rentalEntity2);
        when(rentalRepository.findAll()).thenReturn(rentalEntities);
        when(rentalMapper.toDto(rentalEntity1)).thenReturn(rentalResponse1);
        when(rentalMapper.toDto(rentalEntity2)).thenReturn(rentalResponse2);

        RentalsResponse rentalsResponse = rentalQueryService.getRentals();

        assertNotNull(rentalsResponse);
        assertNotNull(rentalsResponse.getRentals());
        assertEquals(2, rentalsResponse.getRentals().size());

        verify(rentalMapper, times(1)).toDto(rentalEntity1);
        verify(rentalMapper, times(1)).toDto(rentalEntity2);
    }

    private RentalEntity buildRentalEntity() {
        LocalDateTime now = LocalDateTime.now();
        return RentalEntity.builder()
                .id(1L)
                .name("test rental")
                .surface(100.0)
                .price(1500.0)
                .picture("http://example.com/pic.jpg")
                .description("Description")
                .created_at(now)
                .updated_at(now)
                .owner_id(42L)
                .build();
    }

    private RentalResponse buildRentalResponse() {
        return RentalResponse.builder()
                .id(1L)
                .name("test rental")
                .surface(100.0)
                .price(1500.0)
                .picture(new String[] { "http://example.com/pic.jpg" })
                .description("Description")
                .build();
    }
}
