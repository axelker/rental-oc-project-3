package com.openclassrooms.rental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.rental.dto.response.RentalResponse;
import com.openclassrooms.rental.model.RentalEntity;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    @Mapping(target = "picture", expression = "java(mapPictureToArray(entity.getPicture()))")
    RentalResponse toDto(RentalEntity entity);

    default String[] mapPictureToArray(String picture) {
        return new String[] { picture };
    }
}
