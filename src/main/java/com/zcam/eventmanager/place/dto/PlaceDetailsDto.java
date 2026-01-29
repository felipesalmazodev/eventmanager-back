package com.zcam.eventmanager.place.dto;

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
}
