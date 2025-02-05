package com.openclassrooms.rental.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDtoResponse {
    private Long id;
	private String name;
	private String email;
	private LocalDate created_at;
	private LocalDate updated_at;
}
