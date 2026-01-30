package com.zcam.eventmanager.event.mapper;

import com.zcam.eventmanager.event.dto.EventCreateRequest;
import com.zcam.eventmanager.event.dto.EventDetailsDto;
import com.zcam.eventmanager.event.dto.EventListDto;
import com.zcam.eventmanager.event.dto.EventUpdateRequest;
import com.zcam.eventmanager.event.model.Event;
import com.zcam.eventmanager.place.mapper.PlaceMapper;
import com.zcam.eventmanager.place.model.Place;
import com.zcam.eventmanager.placeaddress.dto.PlaceAddressDto;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    private final PlaceMapper placeMapper;

    public EventMapper(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }

    public Event toEntity(EventCreateRequest request, Place place) {
        return new Event(
                request.name(),
                request.startsAt(),
                request.finishesAt(),
                place,
                request.description()
        );
    }

    public EventDetailsDto toEventDetailsDto(Event event) {
        return new EventDetailsDto(
                event.getId(),
                event.getName(),
                event.getStartsAt(),
                event.getFinishesAt(),
                event.getDescription(),
                event.getUpdatedAt(),
                event.getCreatedAt(),
                placeMapper.toPlaceDetailsDto(event.getPlace())
        );
    }

    public EventDetailsDto toEventDetailsWithEnrichmentDto(Event event, PlaceAddressDto placeAddressDto) {
        return new EventDetailsDto(
                event.getId(),
                event.getName(),
                event.getStartsAt(),
                event.getFinishesAt(),
                event.getDescription(),
                event.getUpdatedAt(),
                event.getCreatedAt(),
                placeMapper.toPlaceDetailsDto(event.getPlace()),
                placeAddressDto
        );
    }

    public EventListDto toEventListDto(Event event) {
        return new EventListDto(
                event.getId(),
                event.getName(),
                event.getStartsAt(),
                event.getFinishesAt(),
                placeMapper.toPlaceDetailsDto(event.getPlace()),
                event.getDescription()
        );
    }

    public void applyUpdate(Event event, Place place, EventUpdateRequest request){
        event.update(
                request.name(),
                request.startsAt(),
                request.finishesAt(),
                request.description(),
                place
        );
    }
}
