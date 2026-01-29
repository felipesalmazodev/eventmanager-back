package com.zcam.eventmanager.place.dto;

import com.zcam.eventmanager.place.model.Place;

public record PlaceListDto(
        Long id,
        String name,
        String code,
        int capacity,
        boolean available,
        String cep
) {
    public PlaceListDto(Place place) {
        this(place.getId(), place.getName(), place.getCode(), place.getCapacity(), place.isAvailable(), place.getCep());
    }
}