package com.zcam.eventmanager.event.dto;

import com.zcam.eventmanager.place.dto.PlaceDetailsDto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Summary representation of an event for listing purposes")
public record EventListDto(

        @Schema(description = "Unique identifier of the event", example = "10")
        long id,

        @Schema(description = "Event name", example = "Java Conference 2026")
        String name,

        @Schema(description = "Event start date and time", example = "2026-03-10T09:00:00")
        LocalDateTime startsAt,

        @Schema(description = "Event end date and time", example = "2026-03-10T18:00:00")
        LocalDateTime finishesAt,

        @Schema(description = "Place where the event will occur")
        PlaceDetailsDto place,

        @Schema(description = "Short event description", example = "Annual Java and Spring conference", nullable = true)
        String description
) {
}
