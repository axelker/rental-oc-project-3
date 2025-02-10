package com.openclassrooms.rental.service.command;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.dto.request.AuthRegisterRequest;
import com.openclassrooms.rental.mapper.UserMapper;
import com.openclassrooms.rental.repository.UserRepository;

@Service
public class UserCommandService {
    private final UserRepository userRepository;

    public UserCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(AuthRegisterRequest request) {
        // todo : manage users and password in spring security.
        var user = UserMapper.authRegistertoEntity(request);
        userRepository.save(UserMapper.authRegistertoEntity(request));
    }
}
