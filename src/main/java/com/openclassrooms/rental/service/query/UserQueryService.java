package com.openclassrooms.rental.service.query;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.rental.model.UserEntity;
import com.openclassrooms.rental.repository.UserRepository;

@Service
public class UserQueryService {
    private final UserRepository userRepository;

    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(Integer id) throws NoSuchElementException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

}
