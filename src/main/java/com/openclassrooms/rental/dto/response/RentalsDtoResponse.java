package com.openclassrooms.rental.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RentalsDtoResponse {
    List<RentalDtoResponse> rentals;
}
