package com.zcam.eventmanager.viacep.infra.service;

import com.zcam.eventmanager.placeaddress.document.PlaceAddress;
import com.zcam.eventmanager.placeaddress.repository.PlaceAddressRepository;
import com.zcam.eventmanager.viacep.dto.ViaCepResponse;
import com.zcam.eventmanager.viacep.infra.client.ViaCepClient;
import com.zcam.eventmanager.viacep.utils.CepUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ViaCepService {

    private final ViaCepClient viaCepClient;
    private final PlaceAddressRepository placeAddressRepository;

    public ViaCepService(ViaCepClient viaCepClient, PlaceAddressRepository placeAddressRepository) {
        this.viaCepClient = viaCepClient;
        this.placeAddressRepository = placeAddressRepository;
    }

    public PlaceAddress fetchData(String cep) {
        String normalizedCep = CepUtils.normalize(cep);
        CepUtils.validateOrThrow(normalizedCep);

        ViaCepResponse response = viaCepClient.doRequest(normalizedCep);

        PlaceAddress doc = placeAddressRepository.findByCep(normalizedCep).orElseGet(PlaceAddress::new);
        doc.setCep(normalizedCep);
        doc.setLogradouro(response.getLogradouro());
        doc.setBairro(response.getBairro());
        doc.setLocalidade(response.getLocalidade());
        doc.setUf(response.getUf());
        doc.setFetchedAt(Instant.now());

        return placeAddressRepository.save(doc);
    }
}
