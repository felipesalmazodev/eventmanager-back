package com.zcam.eventmanager.event.service;

import com.zcam.eventmanager.event.dto.EventCreateRequest;
import com.zcam.eventmanager.event.dto.EventDetailsDto;
import com.zcam.eventmanager.event.dto.EventListDto;
import com.zcam.eventmanager.event.dto.EventUpdateRequest;
import com.zcam.eventmanager.event.model.Event;
import com.zcam.eventmanager.event.repository.EventRepository;
import com.zcam.eventmanager.place.model.Place;
import com.zcam.eventmanager.place.repository.PlaceRepository;
import com.zcam.eventmanager.shared.exceptions.DateMismatchException;
import com.zcam.eventmanager.shared.exceptions.PlaceUnavailabilityException;
import com.zcam.eventmanager.shared.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Event createEvent(EventCreateRequest request) {
        if (request.finishesAt().isBefore(request.startsAt())) throw new DateMismatchException("The end date cannot be less than start date");

        Event event = request.toEntity();

        Place place = placeRepository.findByCode(request.placeCode())
                .orElseThrow(() -> new ResourceNotFoundException("Place with code '%s' doesn't exists.".formatted(request.placeCode())));

        if (eventRepository.existsPlaceConflict(request.placeCode(), request.startsAt(), request.finishesAt())) {
            throw new PlaceUnavailabilityException("This place is not available for this date and hour");
        }

        event.setPlace(place);

        return eventRepository.save(event);
    }

    public EventDetailsDto getEventDetails(long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with id '%s' doesn't exists".formatted(id)));
        return new EventDetailsDto(event);
    }

    @Transactional
    public void updateEvent(EventUpdateRequest request, long id) {
        if (request.finishesAt().isBefore(request.startsAt())) throw new DateMismatchException("The end date cannot be less than start date");

        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with id '%s' doesn't exists".formatted(id)));

        Place place = placeRepository.findByCode(request.placeCode())
                .orElseThrow(() -> new ResourceNotFoundException("Place with code '%s' doesn't exists.".formatted(request.placeCode())));

        if (eventRepository.existsPlaceConflictExcludingEvent(request.placeCode(), id, request.startsAt(), request.finishesAt())) {
            throw new PlaceUnavailabilityException("This place is not available for this date and hour");
        }

        event.setPlace(place);
    }

    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }
}
