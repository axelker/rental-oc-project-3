package com.openclassrooms.rental.dto.request;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AuthLoginRequest {
    private String email;
    private String password;
}