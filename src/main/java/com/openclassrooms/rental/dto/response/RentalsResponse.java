package com.openclassrooms.rental.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RentalsResponse {
    List<RentalResponse> rentals;
}
