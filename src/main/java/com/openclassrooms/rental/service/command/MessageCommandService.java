package com.openclassrooms.rental.service.command;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.model.MessageEntity;
import com.openclassrooms.rental.repository.MessageRepository;

@Service
public class MessageCommandService {

    private final MessageRepository messageRepository;

    public MessageCommandService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void createMessage(MessageEntity message) {
        messageRepository.save(message);
    }
}
