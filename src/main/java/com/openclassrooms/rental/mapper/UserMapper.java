package com.openclassrooms.rental.mapper;

import com.openclassrooms.rental.dto.response.UserResponse;
import com.openclassrooms.rental.model.UserEntity;

public class UserMapper {

    public static UserResponse toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserResponse response = UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .created_at(entity.getCreated_at())
                .updated_at(entity.getUpdated_at())
                .build();

        return response;
    }
}