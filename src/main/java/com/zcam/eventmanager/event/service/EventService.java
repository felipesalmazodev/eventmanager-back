package com.zcam.eventmanager.event.service;

import com.zcam.eventmanager.event.dto.EventCreateRequest;
import com.zcam.eventmanager.event.dto.EventListDto;
import com.zcam.eventmanager.event.model.Event;
import com.zcam.eventmanager.event.repository.EventRepository;
import com.zcam.eventmanager.place.model.Place;
import com.zcam.eventmanager.place.repository.PlaceRepository;
import com.zcam.eventmanager.shared.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;

    public EventService(EventRepository eventRepository, PlaceRepository placeRepository) {
        this.eventRepository = eventRepository;
        this.placeRepository = placeRepository;
    }

    public List<EventListDto> getAllEvents() {
        return eventRepository.findAll().stream().map(EventListDto::new).toList();
    }

    public Event createEvent(EventCreateRequest request) {
        if (request.finishesAt().isBefore(request.startsAt())) throw new RuntimeException("meu deus do ceu");

        Event event = request.toEntity();

        Place place = placeRepository.findByCode(request.placeCode())
                .orElseThrow(() -> new ResourceNotFoundException("Place with code '%s' doesn't exists.".formatted(request.placeCode())));

        event.setPlace(place);

        return eventRepository.save(event);
    }
}
