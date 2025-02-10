package com.openclassrooms.rental.mapper;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.model.MessageEntity;
import com.openclassrooms.rental.model.RentalEntity;
import com.openclassrooms.rental.model.UserEntity;

public class MessageMapper {
    
    public static MessageEntity toEntity(MessageRequest message) {
        if (message == null) {
            return null;
        }
        MessageEntity entity = new MessageEntity();
        entity.setUser(UserEntity.builder().id(message.getUser_id()).build());
        entity.setRental(RentalEntity.builder().id(message.getRental_id()).build());
        entity.setMessage(message.getMessage());
        return entity;
    }
}
