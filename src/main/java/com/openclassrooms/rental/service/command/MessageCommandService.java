package com.openclassrooms.rental.service.command;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.mapper.MessageMapper;
import com.openclassrooms.rental.repository.MessageRepository;

@Service
public class MessageCommandService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageCommandService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public void createMessage(MessageRequest message) {
        messageRepository.save(messageMapper.toEntity(message));
    }
}
