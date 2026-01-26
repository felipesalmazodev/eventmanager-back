package com.zcam.eventmanager.place.controller;

import com.zcam.eventmanager.place.service.PlaceService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }
}
