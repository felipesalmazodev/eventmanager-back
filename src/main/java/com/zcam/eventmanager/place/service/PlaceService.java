package com.zcam.eventmanager.place.service;

import com.zcam.eventmanager.place.dto.PlaceCreateRequest;
import com.zcam.eventmanager.place.dto.PlaceListDto;
import com.zcam.eventmanager.place.model.Place;
import com.zcam.eventmanager.place.repository.PlaceRepository;
import com.zcam.eventmanager.shared.exceptions.DuplicateResourceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<PlaceListDto> getAllPlaces() {
        return placeRepository.findAll().stream().map(PlaceListDto::new).toList();
    }

    public Place createPlace(PlaceCreateRequest request) {
        if (placeRepository.existsByCode(request.code())) {
            throw new DuplicateResourceException(
                    "Place with code '%s' already exists".formatted(request.code())
            );
        }

        return placeRepository.save(new Place(
                request.name(),
                request.code(),
                request.capacity(),
                request.cep(),
                request.number(),
                request.complement(),
                request.reference()
        ));
    }
}
