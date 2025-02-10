package com.openclassrooms.rental.service.query;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.response.UserResponse;
import com.openclassrooms.rental.mapper.UserMapper;
import com.openclassrooms.rental.model.UserEntity;
import com.openclassrooms.rental.repository.UserRepository;

@Service
public class UserQueryService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserQueryService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse getUserById(Integer id) throws NoSuchElementException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return userMapper.toDto(user);
    }

}
