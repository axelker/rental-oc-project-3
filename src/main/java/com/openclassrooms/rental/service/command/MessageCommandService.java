package com.openclassrooms.rental.service.command;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.mapper.MessageMapper;
import com.openclassrooms.rental.repository.MessageRepository;

@Service
public class MessageCommandService {
    
    private final MessageRepository messageRepository;


    public MessageCommandService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public void createMessage(MessageRequest message) {
        messageRepository.save(MessageMapper.toEntity(message));
    }
}
