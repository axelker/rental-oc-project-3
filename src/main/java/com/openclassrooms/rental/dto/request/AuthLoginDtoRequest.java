package com.openclassrooms.rental.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginDtoRequest {
    private String email;
    private String password;
}