package com.openclassrooms.rental.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRegisterRequest {
    private String name;
    private String email;
    private String password;
}
