package com.zcam.eventmanager.place.controller;

import com.zcam.eventmanager.place.dto.PlaceCreateRequest;
import com.zcam.eventmanager.place.dto.PlaceListDto;
import com.zcam.eventmanager.place.model.Place;
import com.zcam.eventmanager.place.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/api/places")
    public ResponseEntity<List<PlaceListDto>> getAllPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @PostMapping("/api/places/create")
    public ResponseEntity<?> create (@Valid @RequestBody PlaceCreateRequest request) {
        Place place = placeService.createPlace(request);

        return ResponseEntity.created(URI.create("/api/places/" + place.getId())).build();
    }
}
