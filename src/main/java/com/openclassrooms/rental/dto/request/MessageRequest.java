package com.openclassrooms.rental.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    private String message;
    private Integer user_id;
    private Integer rental_id;
}
