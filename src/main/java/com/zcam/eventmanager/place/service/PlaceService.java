package com.zcam.eventmanager.place.service;

import com.zcam.eventmanager.place.repository.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }
}
