package com.zcam.eventmanager.event.dto;

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
}
