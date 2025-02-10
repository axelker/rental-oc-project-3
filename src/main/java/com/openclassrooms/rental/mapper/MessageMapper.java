package com.openclassrooms.rental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.model.MessageEntity;
import com.openclassrooms.rental.model.RentalEntity;
import com.openclassrooms.rental.model.UserEntity;

@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "id", ignore = true)

    @Mapping(target = "user", source = "user_id", qualifiedByName = "mapUserById")
    @Mapping(target = "rental", source = "rental_id", qualifiedByName = "mapRentalById")
    MessageEntity toEntity(MessageRequest message);

    @Named("mapUserById")
    default UserEntity mapUserById(Integer userId) {
        return userId == null ? null : UserEntity.builder().id(userId).build();
    }

    @Named("mapRentalById")
    default RentalEntity mapRentalById(Integer rentalId) {
        return rentalId == null ? null : RentalEntity.builder().id(rentalId).build();
    }
}
