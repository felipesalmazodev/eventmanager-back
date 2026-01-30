package com.zcam.eventmanager.place.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detailed representation of a place")
public record PlaceDetailsDto(

        @Schema(description = "Place name", example = "Auditório Central")
        String name,

        @Schema(description = "Unique code of the place", example = "AUDCENTRAL")
        String code,

        @Schema(description = "CEP (Brazilian postal code) with exactly 8 digits", example = "01001000")
        String cep,

        @Schema(description = "Maximum capacity of the place", example = "150")
        int capacity,

        @Schema(description = "Street number of the place address", example = "100")
        int number,

        @Schema(description = "Optional address complement", example = "Block B, second floor", nullable = true)
        String complement,

        @Schema(description = "Optional address reference", example = "Next to the central park", nullable = true)
        String reference
) {
}
