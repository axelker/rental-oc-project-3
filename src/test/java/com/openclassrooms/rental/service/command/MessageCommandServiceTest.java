package com.openclassrooms.rental.service.command;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.mapper.MessageMapper;
import com.openclassrooms.rental.model.MessageEntity;
import com.openclassrooms.rental.repository.MessageRepository;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MessageCommandServiceTest {
    @Mock
    private MessageMapper messageMapper;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageCommandService messageCommandService;

    @Test
    void createMessageSuccess() {
        MessageRequest messageRequest = MessageRequest.builder()
                .user_id(1L)
                .rental_id(2L)
                .message("test message")
                .build();

        MessageEntity messageEntity = MessageEntity.builder()
                .message("test message")
                .build();

        when(messageMapper.toEntity(messageRequest)).thenReturn(messageEntity);
        when(messageRepository.save(messageEntity)).thenReturn(messageEntity);

        messageCommandService.createMessage(messageRequest);
        verify(messageRepository, times(1)).save(messageEntity);
    }

}
