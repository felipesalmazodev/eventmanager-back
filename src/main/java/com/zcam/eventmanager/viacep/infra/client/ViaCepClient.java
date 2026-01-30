package com.zcam.eventmanager.viacep.infra.client;

import com.zcam.eventmanager.viacep.dto.ViaCepResponse;
import com.zcam.eventmanager.viacep.infra.exceptions.CepNotFoundException;
import com.zcam.eventmanager.viacep.infra.exceptions.ViaCepIntegrationException;
import com.zcam.eventmanager.viacep.utils.CepUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class ViaCepClient {
    private static final String BASE_URL = "https://viacep.com.br/ws";

    private final RestTemplate restTemplate;

    public ViaCepClient(RestTemplate viaCepRestTemplate) {
        this.restTemplate = viaCepRestTemplate;
    }

    public ViaCepResponse doRequest(String cepRaw) {
        String cep = CepUtils.normalize(cepRaw);
        CepUtils.validateOrThrow(cep);

        URI uri = UriComponentsBuilder
                .fromUriString(BASE_URL)
                .pathSegment(cep, "json")
                .build()
                .toUri();

        try {
            ResponseEntity<ViaCepResponse> response = restTemplate.getForEntity(uri, ViaCepResponse.class);

            ViaCepResponse body = response.getBody();
            if (body == null) {
                throw new ViaCepIntegrationException("ViaCEP returned empty body for CEP: %s".formatted(cep));
            }

            if (Boolean.TRUE.equals(body.getErro())) {
                throw new CepNotFoundException(cep);
            }

            return body;

        } catch (ResourceAccessException e) {
            throw new ViaCepIntegrationException("ViaCEP network/timeout error for CEP: %s".formatted(cep));

        } catch (RestClientException e) {
            throw new ViaCepIntegrationException("ViaCEP unexpected error for CEP: %s".formatted(cep));
        }
    }
}
