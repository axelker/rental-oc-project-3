package com.openclassrooms.rental.service.command;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.request.AuthRegisterRequest;
import com.openclassrooms.rental.mapper.UserMapper;
import com.openclassrooms.rental.repository.UserRepository;

@Service
public class UserCommandService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserCommandService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void createUser(AuthRegisterRequest request) {
        // todo : manage users and password in spring security.
        var user = userMapper.authRegisterToEntity(request);
        userRepository.save(userMapper.authRegisterToEntity(request));
    }
}
