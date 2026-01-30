package com.zcam.eventmanager.event.dto;

import com.zcam.eventmanager.place.dto.PlaceDetailsDto;
import com.zcam.eventmanager.placeaddress.dto.PlaceAddressDto;

import java.time.LocalDateTime;

public record EventDetailsDto(
        long id,
        String name,
        LocalDateTime startsAt,
        LocalDateTime finishesAt,
        String description,
        LocalDateTime updatedAt,
        LocalDateTime createdAt,
        PlaceDetailsDto place,
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
