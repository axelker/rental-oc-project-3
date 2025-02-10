package com.openclassrooms.rental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@Table(name = "rentals")
public class RentalEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double surface;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer owner_id;
}