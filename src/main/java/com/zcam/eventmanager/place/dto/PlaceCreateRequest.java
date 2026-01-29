package com.zcam.eventmanager.place.dto;

import com.zcam.eventmanager.place.model.Place;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PlaceCreateRequest(
        @NotBlank(message = "This field is mandatory")
        @Size(max = 255)
        String name,

        @NotBlank(message = "This field is mandatory")
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Only numbers and letters is permitted")
        @Size(max = 255)
        String code,

        @NotNull(message = "This field is mandatory")
        int capacity,

        @NotBlank(message = "This field is mandatory")
        @Pattern(regexp = "^\\d{8}$", message = "The CEP must contain 8 numbers")
        String cep,

        @NotNull(message = "This field is mandatory")
        int number,

        String complement,
        String reference
) {
        public Place toEntity() {
                return new Place(
                        name,
                        code,
                        capacity,
                        cep,
                        number,
                        complement,
                        reference
                );
        }
}