package com.zcam.eventmanager.place.dto;

public record PlaceUpdateRequest(
        String name,
        String code,
        int capacity,
        boolean available,
        String cep,
        int number,
        String complement,
        String reference
) {
}