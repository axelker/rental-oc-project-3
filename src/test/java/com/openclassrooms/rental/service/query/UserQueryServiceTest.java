package com.openclassrooms.rental.service.query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.openclassrooms.rental.model.UserEntity;
import com.openclassrooms.rental.dto.response.UserResponse;
import com.openclassrooms.rental.repository.UserRepository;

import java.util.NoSuchElementException;
import com.openclassrooms.rental.mapper.UserMapper;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserQueryService userQueryService;

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .email("test@test.fr")
                .name("test")
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .password("test-secret")
                .build();

        UserResponse userResponse = UserResponse.builder().id(1).email("test@test.fr").name("test").build();
        when(userRepository.findById(1))
                .thenReturn(Optional.of(userEntity));
        when(userMapper.toDto(userEntity)).thenReturn(userResponse);
        var result = userQueryService.getUserById(1);

        // Vérifications
        assertNotNull(result);
        assertEquals(userResponse.getId(), result.getId());
        assertEquals(userResponse.getEmail(), result.getEmail());
        assertEquals(userResponse.getName(), result.getName());
        verify(userMapper, times(1)).toDto(userEntity);

    }

    @Test
    void getUserById_shouldThrowException_whenUserDoesNotExist() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userQueryService.getUserById(2);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userMapper, never()).toDto(any());

    }
}
