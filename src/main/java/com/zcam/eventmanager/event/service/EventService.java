package com.zcam.eventmanager.event.service;

import com.zcam.eventmanager.event.dto.EventCreateRequest;
import com.zcam.eventmanager.event.dto.EventDetailsDto;
import com.zcam.eventmanager.event.dto.EventListDto;
import com.zcam.eventmanager.event.dto.EventUpdateRequest;
import com.zcam.eventmanager.event.mapper.EventMapper;
import com.zcam.eventmanager.event.model.Event;
import com.zcam.eventmanager.event.repository.EventRepository;
import com.zcam.eventmanager.place.model.Place;
import com.zcam.eventmanager.place.repository.PlaceRepository;
import com.zcam.eventmanager.placeaddress.document.PlaceAddress;
import com.zcam.eventmanager.placeaddress.dto.PlaceAddressDto;
import com.zcam.eventmanager.shared.exceptions.DateMismatchException;
import com.zcam.eventmanager.shared.exceptions.PlaceUnavailabilityException;
import com.zcam.eventmanager.shared.exceptions.ResourceNotFoundException;
import com.zcam.eventmanager.viacep.infra.service.ViaCepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final EventMapper eventMapper;
    private final ViaCepService viaCepService;

    public EventService(EventRepository eventRepository, PlaceRepository placeRepository, EventMapper eventMapper, ViaCepService viaCepService) {
        this.eventRepository = eventRepository;
        this.placeRepository = placeRepository;
        this.eventMapper = eventMapper;
        this.viaCepService = viaCepService;
    }

    public List<EventListDto> getAllEvents() {
        return eventRepository.findAll().stream().map(eventMapper::toEventListDto).toList();
    }

    @Transactional
    public Event createEvent(EventCreateRequest request) {
        if (request.finishesAt().isBefore(request.startsAt())) throw new DateMismatchException("The end date cannot be less than start date");

        Place place = placeRepository.findByCodeAndActiveTrue(request.placeCode())
                .orElseThrow(() -> new ResourceNotFoundException("Place with code '%s' doesn't exists.".formatted(request.placeCode())));

        if (eventRepository.existsPlaceConflict(request.placeCode(), request.startsAt(), request.finishesAt())) {
            throw new PlaceUnavailabilityException("This place is not available for this date and hour");
        }

        return eventRepository.save(eventMapper.toEntity(request, place));
    }

    public EventDetailsDto getEventDetails(long id, boolean enrichPlace) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with id '%s' doesn't exists".formatted(id)));

        if (!enrichPlace) return eventMapper.toEventDetailsDto(event);

        PlaceAddress placeAddress = viaCepService.fetchData(event.getPlaceCep());

        PlaceAddressDto placeAddressDto = new PlaceAddressDto(
                placeAddress.getCep(),
                placeAddress.getLogradouro(),
                placeAddress.getBairro(),
                placeAddress.getLocalidade(),
                placeAddress.getUf()
        );

        return eventMapper.toEventDetailsWithEnrichmentDto(event, placeAddressDto);
    }

    @Transactional
    public void updateEvent(EventUpdateRequest request, long id) {
        if (request.finishesAt().isBefore(request.startsAt())) throw new DateMismatchException("The end date cannot be less than start date");

        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with id '%s' doesn't exists".formatted(id)));

        Place place = placeRepository.findByCodeAndActiveTrue(request.placeCode())
                .orElseThrow(() -> new ResourceNotFoundException("Place with code '%s' doesn't exists.".formatted(request.placeCode())));

        if (eventRepository.existsPlaceConflictExcludingEvent(request.placeCode(), id, request.startsAt(), request.finishesAt())) {
            throw new PlaceUnavailabilityException("This place is not available for this date and hour");
        }

        eventMapper.applyUpdate(event, place, request);
    }

    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }
}
