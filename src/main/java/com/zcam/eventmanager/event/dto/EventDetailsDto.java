package com.zcam.eventmanager.event.dto;

import com.zcam.eventmanager.event.model.Event;
import com.zcam.eventmanager.place.dto.PlaceDetailsDto;

import java.time.LocalDateTime;

public record EventDetailsDto(
        long id,
        String name,
        LocalDateTime startsAt,
        LocalDateTime finishesAt,
        String description,
        LocalDateTime updatedAt,
        LocalDateTime createdAt,
        PlaceDetailsDto place
) {
    public EventDetailsDto(Event event) {
        this(event.getId(), event.getName(), event.getStartsAt(), event.getFinishesAt(), event.getDescription(), event.getUpdatedAt(), event.getCreatedAt(), new PlaceDetailsDto(event.getPlace()));
    }
}
