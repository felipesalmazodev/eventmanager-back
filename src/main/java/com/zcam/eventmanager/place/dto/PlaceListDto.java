package com.zcam.eventmanager.place.dto;

public record PlaceListDto(
        Long id,
        String name,
        String code,
        int capacity,
        boolean available,
        String cep
) {
}