package com.openclassrooms.rental.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRegisterDtoRequest {
    private String name;
    private String email;
    private String password;
}
