package com.openclassrooms.rental.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
	private String name;
	private String email;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
}
