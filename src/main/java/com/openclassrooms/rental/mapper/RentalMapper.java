package com.openclassrooms.rental.mapper;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.model.RentalEntity;

public class RentalMapper {

    public static RentalResponse toDto(RentalEntity entity) {
        if (entity == null) {
            return null;
        }

        RentalResponse response = RentalResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .surface(entity.getSurface())
                .price(entity.getPrice())
                .picture(entity.getPicture())
                .description(entity.getDescription())
                .owner_id(entity.getOwner_id())
                .created_at(entity.getCreated_at())
                .updated_at(entity.getUpdated_at())
                .build();
        return response;
    }
}
