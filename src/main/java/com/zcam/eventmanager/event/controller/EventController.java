package com.zcam.eventmanager.event.controller;

import com.zcam.eventmanager.event.dto.EventCreateRequest;
import com.zcam.eventmanager.event.dto.EventDetailsDto;
import com.zcam.eventmanager.event.dto.EventListDto;
import com.zcam.eventmanager.event.dto.EventUpdateRequest;
import com.zcam.eventmanager.event.model.Event;
import com.zcam.eventmanager.event.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class EventController {
    
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    
    @GetMapping("/api/events")
    public ResponseEntity<List<EventListDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping("/api/events/create")
    public ResponseEntity<?> create(@Valid @RequestBody EventCreateRequest request) {
        Event event = eventService.createEvent(request);
        return ResponseEntity.created(URI.create("/api/events/" + event.getId())).build();
    }

    @GetMapping("/api/events/{id}")
    public ResponseEntity<EventDetailsDto> getEventDetails(@PathVariable long id) {
        return ResponseEntity.ok(eventService.getEventDetails(id));
    }

    @PutMapping("/api/events/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EventUpdateRequest request, @PathVariable long id) {
        eventService.updateEvent(request, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/events/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
