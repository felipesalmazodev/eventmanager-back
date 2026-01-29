package com.zcam.eventmanager.place.dto;

import jakarta.validation.constraints.*;

public record PlaceCreateRequest(
        @NotBlank(message = "This field is mandatory")
        @Size(max = 255)
        String name,

        @NotBlank(message = "This field is mandatory")
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Only numbers and letters is permitted")
        @Size(max = 255)
        String code,

        @Min(value = 10, message = "The minimum capacity is 10")
        int capacity,

        @NotBlank(message = "This field is mandatory")
        @Pattern(regexp = "^\\d{8}$", message = "The CEP must contain 8 numbers")
        String cep,

        @NotNull(message = "This field is mandatory")
        int number,

        String complement,
        String reference
) {
}