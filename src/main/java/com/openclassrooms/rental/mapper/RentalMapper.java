package com.openclassrooms.rental.mapper;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.model.RentalEntity;

public class RentalMapper {

    public static RentalResponse toDto(RentalEntity entity) {
        if (entity == null) {
            return null;
        }

        RentalResponse response = new RentalResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setSurface(entity.getSurface());
        response.setPrice(entity.getPrice());
        response.setPicture(entity.getPicture());
        response.setDescription(entity.getDescription());
        response.setOwner_id(entity.getOwner_id());
        response.setCreated_at(entity.getCreated_at());
        response.setUpdated_at(entity.getUpdated_at());

        return response;
    }
}
