package com.openclassrooms.rental.service.query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.openclassrooms.rental.model.UserEntity;
import com.openclassrooms.rental.repository.UserRepository;

import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceTest {

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

        when(userRepository.findById(1)).thenReturn(Optional.of(userEntity));

        var result = userQueryService.getUserById(1);

        // Vérifications
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("test@test.fr", result.getEmail());
        assertEquals("test", result.getName());
    }

    @Test
    void getUserById_shouldThrowException_whenUserDoesNotExist() {
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userQueryService.getUserById(2);
        });

        assertEquals("User not found", exception.getMessage());
    }
}
