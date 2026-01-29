package com.zcam.eventmanager.event.dto;

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
}
