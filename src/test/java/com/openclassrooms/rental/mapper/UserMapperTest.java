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
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setEmail("test@test.fr");
        userEntity.setName("test");
        userEntity.setCreated_at(LocalDateTime.now());
        userEntity.setUpdated_at(LocalDateTime.now());
        userEntity.setPassword("test-sectret");

        UserResponse userResponse = UserMapper.toDto(userEntity);

        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isEqualTo(userEntity.getId());
        assertThat(userResponse.getName()).isEqualTo(userEntity.getName());
        assertThat(userResponse.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(userResponse.getCreated_at()).isEqualTo(userEntity.getCreated_at());
        assertThat(userResponse.getUpdated_at()).isEqualTo(userEntity.getUpdated_at());
    }
}