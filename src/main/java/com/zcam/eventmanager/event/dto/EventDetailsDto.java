package com.zcam.eventmanager.event.dto;

import com.zcam.eventmanager.place.dto.PlaceDetailsDto;
import com.zcam.eventmanager.placeaddress.dto.PlaceAddressDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Detailed representation of an event")
public record EventDetailsDto(
        @Schema(description = "Unique identifier of the event", example = "10")
        long id,

        @Schema(description = "Event name", example = "Java Conference 2026")
        String name,

        @Schema(description = "Event start date and time", example = "2026-03-10T09:00:00")
        LocalDateTime startsAt,

        @Schema(description = "Event end date and time", example = "2026-03-10T18:00:00")
        LocalDateTime finishesAt,

        @Schema(description = "Event description", example = "Annual conference focused on Java and Spring ecosystem")
        String description,

        @Schema(description = "Date and time when the event was last updated", example = "2026-02-01T14:32:10")
        LocalDateTime updatedAt,

        @Schema(description = "Date and time when the event was created", example = "2026-01-15T10:00:00")
        LocalDateTime createdAt,

        @Schema(description = "Place where the event will occur")
        PlaceDetailsDto place,

        @Schema(description = "Address details of the place (available when enrichment is enabled)", nullable = true)
        PlaceAddressDto address
) {
    public EventDetailsDto(long id, String name, LocalDateTime startsAt, LocalDateTime finishesAt, String description, LocalDateTime updatedAt, LocalDateTime createdAt, PlaceDetailsDto place) {
        this(id, name, startsAt, finishesAt, description, updatedAt, createdAt, place, null);
    }

    public EventDetailsDto(long id, String name, LocalDateTime startsAt, LocalDateTime finishesAt, String description, LocalDateTime updatedAt, LocalDateTime createdAt, PlaceDetailsDto place, PlaceAddressDto address) {
        this.id = id;
        this.name = name;
        this.startsAt = startsAt;
        this.finishesAt = finishesAt;
        this.description = description;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.place = place;
        this.address = address;
    }
}
