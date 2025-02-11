package com.openclassrooms.rental.dto.request;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AuthLoginRequest {
    private String login;
    private String password;
}