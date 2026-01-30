package com.zcam.eventmanager.placeaddress.dto;

public record PlaceAddressDto(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
) {
}
