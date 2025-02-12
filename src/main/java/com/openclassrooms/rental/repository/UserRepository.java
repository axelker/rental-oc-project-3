package com.openclassrooms.rental.repository;

import com.openclassrooms.rental.model.UserEntity;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
