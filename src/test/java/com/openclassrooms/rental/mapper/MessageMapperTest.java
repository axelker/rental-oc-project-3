package com.openclassrooms.rental.mapper;

import org.junit.jupiter.api.Test;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.model.MessageEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class MessageMapperTest {
    private final MessageMapper messageMapper = MessageMapper.INSTANCE;

    @Test
    void shouldMapMessageRequestNullToNull() {
        MessageRequest message = null;
        MessageEntity messageEntity = messageMapper.toEntity(message);

        assertThat(messageEntity).isNull();
    }

    @Test
    void shouldMapMapMessageRequestToMessageEntity() {
        MessageRequest message = MessageRequest.builder().user_id(1L).rental_id(2L).message("test").build();
        MessageEntity messageEntity = messageMapper.toEntity(message);

        assertThat(messageEntity).isNotNull();
        assertThat(messageEntity.getUser().getId()).isEqualTo(message.getUser_id());
        assertThat(messageEntity.getRental().getId()).isEqualTo(message.getRental_id());
        assertThat(messageEntity.getMessage()).isEqualTo(message.getMessage());
    }
}
