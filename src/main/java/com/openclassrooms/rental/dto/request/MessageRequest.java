package com.openclassrooms.rental.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageRequest {
    private String message;
    private Integer user_id;
    private Integer rental_id;
}
