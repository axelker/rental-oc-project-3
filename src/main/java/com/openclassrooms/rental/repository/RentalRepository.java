package com.openclassrooms.rental.repository;

import com.openclassrooms.rental.model.RentalEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Integer> {
}
