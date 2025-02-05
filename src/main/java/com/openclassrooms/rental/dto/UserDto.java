package com.openclassrooms.rental.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
	private String name;
	private String email;
	private LocalDate created_at;
	private LocalDate updated_at;
}
