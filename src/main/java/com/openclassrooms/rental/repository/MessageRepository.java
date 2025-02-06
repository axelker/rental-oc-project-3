package com.openclassrooms.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.rental.model.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {}
