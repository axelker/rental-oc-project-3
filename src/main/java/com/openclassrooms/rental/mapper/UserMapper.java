package com.openclassrooms.rental.mapper;

import com.openclassrooms.rental.dto.response.UserResponse;
import com.openclassrooms.rental.model.UserEntity;

public class UserMapper {

    public static UserResponse toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        response.setCreated_at(entity.getCreated_at());
        response.setUpdated_at(entity.getUpdated_at());

        return response;
    }
}