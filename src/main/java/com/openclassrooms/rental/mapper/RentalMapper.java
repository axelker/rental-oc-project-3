package com.openclassrooms.rental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.model.RentalEntity;

@Mapper
public interface RentalMapper {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    RentalResponse toDto(RentalEntity entity);
}
