package com.zcam.eventmanager.place.dto;

import com.zcam.eventmanager.place.model.Place;

public record PlaceListDto(
        String name,
        String code,
        int capacity,
        boolean available,
        String cep
) {
    public PlaceListDto(Place place) {
        this(place.getName(), place.getCode(), place.getCapacity(), place.isAvailable(), place.getCep());
    }
}