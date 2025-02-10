package com.openclassrooms.rental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.rental.dto.request.AuthRegisterRequest;
import com.openclassrooms.rental.dto.response.UserResponse;
import com.openclassrooms.rental.model.UserEntity;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    UserResponse toDto(UserEntity entity);

    @Mapping(target = "created_at", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updated_at", expression = "java(java.time.LocalDateTime.now())")
    UserEntity authRegisterToEntity(AuthRegisterRequest request);
}