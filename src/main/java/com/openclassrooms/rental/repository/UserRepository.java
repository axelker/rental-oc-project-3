package com.openclassrooms.rental.repository;

import com.openclassrooms.rental.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {}
