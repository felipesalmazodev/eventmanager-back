package com.zcam.eventmanager.event.dto;

import com.zcam.eventmanager.event.model.Event;
import com.zcam.eventmanager.place.dto.PlaceDetailsDto;

import java.time.LocalDateTime;

public record EventListDto(
        long id,
        String name,
        LocalDateTime startsAt,
        LocalDateTime finishesAt,
        PlaceDetailsDto place,
        String description
) {
    public EventListDto(Event event) {
        this(event.getId(), event.getName(), event.getStartsAt(), event.getFinishesAt(), new PlaceDetailsDto(event.getPlace()), event.getDescription());
    }
}
