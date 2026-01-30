package com.zcam.eventmanager.place.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Summary representation of a place for listing purposes")
public record PlaceListDto(

        @Schema(description = "Unique identifier of the place", example = "5")
        Long id,

        @Schema(description = "Place name", example = "Auditório Central")
        String name,

        @Schema(description = "Unique code of the place", example = "AUDCENTRAL")
        String code,

        @Schema(description = "Maximum capacity of the place", example = "150")
        int capacity,

        @Schema(description = "CEP (Brazilian postal code) with exactly 8 digits", example = "01001000")
        String cep
) {
}