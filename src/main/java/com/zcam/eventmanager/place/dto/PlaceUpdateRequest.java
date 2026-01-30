package com.zcam.eventmanager.place.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request used to update an existing place")
public record PlaceUpdateRequest(

        @Schema(description = "Place name", example = "Auditório Central")
        @NotBlank(message = "This field is mandatory")
        @Size(max = 255)
        String name,

        @Schema(description = "Unique code of the place", example = "AUDCENTRAL")
        @NotBlank(message = "This field is mandatory")
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Only numbers and letters is permitted")
        @Size(max = 255)
        String code,

        @Schema(description = "Maximum capacity of the place", example = "150")
        @NotNull
        @Min(value = 10, message = "The minimum capacity is 10")
        Integer capacity,

        @Schema(description = "CEP (Brazilian postal code) with exactly 8 digits", example = "01001000")
        @NotBlank(message = "This field is mandatory")
        @Pattern(regexp = "^\\d{8}$", message = "The CEP must contain 8 numbers")
        String cep,

        @Schema(description = "Street number of the place address", example = "100")
        @NotNull(message = "This field is mandatory")
        Integer number,

        @Schema(description = "Optional address complement", example = "Block B, second floor", nullable = true)
        String complement,

        @Schema(description = "Optional address reference", example = "Next to the central park", nullable = true)
        String reference
) {
}