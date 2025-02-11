package com.openclassrooms.rental.repository;

import org.springframework.data.repository.CrudRepository;
import com.openclassrooms.rental.model.MessageEntity;

public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {
}
