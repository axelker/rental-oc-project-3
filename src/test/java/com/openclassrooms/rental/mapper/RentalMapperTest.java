package com.openclassrooms.rental.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.model.RentalEntity;

import org.junit.jupiter.api.Test;

public class RentalMapperTest {

    @Test
    void shouldMapRentalEntityNullToNull() {
        RentalEntity rentalEntity = null;
        RentalResponse rentalResponse = RentalMapper.toDto(rentalEntity);

        assertThat(rentalResponse).isNull();
    }

    @Test
    void shouldMapRentalEntityToRentalResponse() {
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

        RentalResponse rentalResponse = RentalMapper.toDto(rentalEntity);

        assertThat(rentalResponse).isNotNull();
        assertThat(rentalResponse.getId()).isEqualTo(rentalEntity.getId());
        assertThat(rentalResponse.getName()).isEqualTo(rentalEntity.getName());
        assertThat(rentalResponse.getSurface()).isEqualTo(rentalEntity.getSurface());
        assertThat(rentalResponse.getPrice()).isEqualTo(rentalEntity.getPrice());
        assertThat(rentalResponse.getPicture()).isEqualTo(rentalEntity.getPicture());
        assertThat(rentalResponse.getDescription()).isEqualTo(rentalEntity.getDescription());
        assertThat(rentalResponse.getOwner_id()).isEqualTo(rentalEntity.getOwner_id());
        assertThat(rentalResponse.getCreated_at()).isEqualTo(rentalEntity.getCreated_at());
        assertThat(rentalResponse.getUpdated_at()).isEqualTo(rentalEntity.getUpdated_at());
    }
}
