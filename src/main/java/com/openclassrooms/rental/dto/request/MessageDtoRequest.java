package com.openclassrooms.rental.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDtoRequest {
    private String message;
    private Long user_id;
    private Long rental_id;
}
