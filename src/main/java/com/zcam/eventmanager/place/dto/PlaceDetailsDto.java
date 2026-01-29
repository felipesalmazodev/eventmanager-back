package com.zcam.eventmanager.place.dto;

import com.zcam.eventmanager.place.model.Place;

public record PlaceDetailsDto(
        String name,
        String code,
        String cep,
        int capacity,
        int number,
        boolean available,
        String complement,
        String reference
) {
    public PlaceDetailsDto(Place place) {
       this(place.getName(), place.getCode(), place.getCep(), place.getCapacity(), place.getNumber(), place.isAvailable(), place.getComplement(), place.getReference());
    }
}
