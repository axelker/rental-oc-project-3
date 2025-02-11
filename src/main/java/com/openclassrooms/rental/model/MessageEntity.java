package com.openclassrooms.rental.model;


import jakarta.persistence.*;
import lombok.Getter;

import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@Table(name = "messages")
public class MessageEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2000)
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private RentalEntity rental;
}
