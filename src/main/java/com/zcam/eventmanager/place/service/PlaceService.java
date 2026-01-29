package com.zcam.eventmanager.place.service;

import com.zcam.eventmanager.place.dto.PlaceCreateRequest;
import com.zcam.eventmanager.place.dto.PlaceDetailsDto;
import com.zcam.eventmanager.place.dto.PlaceListDto;
import com.zcam.eventmanager.place.dto.PlaceUpdateRequest;
import com.zcam.eventmanager.place.model.Place;
import com.zcam.eventmanager.place.repository.PlaceRepository;
import com.zcam.eventmanager.shared.exceptions.DuplicateResourceException;
import com.zcam.eventmanager.shared.exceptions.ResourceNotFoundException;
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
            throw new DuplicateResourceException("Place with code '%s' already exists".formatted(request.code()));
        }

        return placeRepository.save(request.toEntity());
    }

    public PlaceDetailsDto getPlaceDetails(Long id) {
        Place place = placeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Place with id '%s' doesn't exists".formatted(id)));
        return new PlaceDetailsDto(place);
    }

    public void updatePlace(PlaceUpdateRequest request, Long id) {
        if(!placeRepository.existsByCodeAndIdNot(request.code(), id)){
            Place place = placeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Place with id '%s' doesn't exists".formatted(id)));
            placeRepository.save(place.update(request));
        } else {
            throw new DuplicateResourceException("Place with code '%s' already exists".formatted(request.code()));
        }
    }

    public void deletePlace(long id) {
        placeRepository.deleteById(id);
    }
}
