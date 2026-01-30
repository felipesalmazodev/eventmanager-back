package com.zcam.eventmanager.placeaddress.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Address details obtained from CEP enrichment")
public record PlaceAddressDto(

        @Schema(description = "CEP (Brazilian postal code) with exactly 8 digits", example = "01001000")
        String cep,

        @Schema(description = "Street name (logradouro)", example = "Praça da Sé")
        String logradouro,

        @Schema(description = "Neighborhood (bairro)", example = "Sé")
        String bairro,

        @Schema(description = "City (localidade)", example = "São Paulo")
        String localidade,

        @Schema(description = "Federative Unity (UF)", example = "SP")
        String uf
) {
}
