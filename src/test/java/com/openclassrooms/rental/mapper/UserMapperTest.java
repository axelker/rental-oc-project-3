package com.openclassrooms.rental.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import com.openclassrooms.rental.dto.response.UserResponse;
import com.openclassrooms.rental.model.UserEntity;

import org.junit.jupiter.api.Test;

public class UserMapperTest {

    @Test
    void shouldMapUserEntityNullToNull() {
        UserEntity userEntity = null;
        UserResponse userResponse = UserMapper.toDto(userEntity);

        assertThat(userResponse).isNull();
    }

    @Test
    void shouldMapUserEntityToUserResponse() {
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .email("test@test.fr")
                .name("test")
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .password("test-secret")
                .build();

        UserResponse userResponse = UserMapper.toDto(userEntity);

        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isEqualTo(userEntity.getId());
        assertThat(userResponse.getName()).isEqualTo(userEntity.getName());
        assertThat(userResponse.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(userResponse.getCreated_at()).isEqualTo(userEntity.getCreated_at());
        assertThat(userResponse.getUpdated_at()).isEqualTo(userEntity.getUpdated_at());
    }
}