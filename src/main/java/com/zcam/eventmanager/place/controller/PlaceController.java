package com.zcam.eventmanager.place.controller;

import com.zcam.eventmanager.place.controller.documentation.PlaceDocumentation;
import com.zcam.eventmanager.place.dto.PlaceCreateRequest;
import com.zcam.eventmanager.place.dto.PlaceDetailsDto;
import com.zcam.eventmanager.place.dto.PlaceListDto;
import com.zcam.eventmanager.place.dto.PlaceUpdateRequest;
import com.zcam.eventmanager.place.model.Place;
import com.zcam.eventmanager.place.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PlaceController implements PlaceDocumentation {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/api/places")
    public ResponseEntity<List<PlaceListDto>> getAllPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @PostMapping("/api/places/create")
    public ResponseEntity<?> create(@Valid @RequestBody PlaceCreateRequest request) {
        Place place = placeService.createPlace(request);
        return ResponseEntity.created(URI.create("/api/places/" + place.getId())).build();
    }

    @GetMapping("/api/places/{id}")
    public ResponseEntity<PlaceDetailsDto> getPlaceDetails(@PathVariable long id) {
        return ResponseEntity.ok(placeService.getPlaceDetails(id));
    }

    @PutMapping("/api/places/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody PlaceUpdateRequest request, @PathVariable long id) {
        placeService.updatePlace(request, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/places/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        placeService.deletePlace(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/places/available")
    public ResponseEntity<List<PlaceListDto>> getAvailablePlacesBetween(
            @RequestParam() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startsAt,
            @RequestParam() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishesAt) {
        return ResponseEntity.ok(placeService.getAvailablePlacesIn(startsAt, finishesAt));
    }

}
