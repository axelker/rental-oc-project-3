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
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setId(1);
        rentalEntity.setName("Luxury Appart");
        rentalEntity.setSurface(85.00);
        rentalEntity.setPrice(1500.00);
        rentalEntity.setPicture("test.jpg");
        rentalEntity.setDescription("Test description");
        rentalEntity.setOwner_id(10);
        rentalEntity.setCreated_at(LocalDateTime.now());
        rentalEntity.setUpdated_at(LocalDateTime.now());

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
