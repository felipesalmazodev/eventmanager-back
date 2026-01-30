package com.zcam.eventmanager.place.mapper;

import com.zcam.eventmanager.place.dto.PlaceCreateRequest;
import com.zcam.eventmanager.place.dto.PlaceDetailsDto;
import com.zcam.eventmanager.place.dto.PlaceListDto;
import com.zcam.eventmanager.place.dto.PlaceUpdateRequest;
import com.zcam.eventmanager.place.model.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {

    public Place toEntity(PlaceCreateRequest request) {
        return new Place(
                request.name(),
                request.code(),
                request.capacity(),
                request.cep(),
                request.number(),
                request.complement(),
                request.reference()
        );
    }

    public PlaceDetailsDto toPlaceDetailsDto(Place place) {
        return new PlaceDetailsDto(
                place.getName(),
                place.getCode(),
                place.getCep(),
                place.getCapacity(),
                place.getNumber(),
                place.getComplement(),
                place.getReference()
        );
    }

    public PlaceListDto toPlaceListDto(Place place) {
        return new PlaceListDto(
                place.getId(),
                place.getName(),
                place.getCode(),
                place.getCapacity(),
                place.getCep()
        );
    }

    public void applyUpdate(Place place, PlaceUpdateRequest request) {
        place.update(
                request.name(),
                request.code(),
                request.capacity(),
                request.cep(),
                request.number(),
                request.complement(),
                request.reference()
        );
    }
}
