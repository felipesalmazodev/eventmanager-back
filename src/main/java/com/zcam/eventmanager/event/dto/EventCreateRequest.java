package com.zcam.eventmanager.event.dto;

import com.zcam.eventmanager.event.model.Event;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record EventCreateRequest(
        @NotBlank(message = "This field is mandatory")
        @Size(max = 255)
        String name,

        @NotNull
        @FutureOrPresent
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime startsAt,

        @NotNull
        @FutureOrPresent
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime finishesAt,

        String placeCode,

        @Size(max = 255)
        String description
) {
    public Event toEntity() {
        return new Event(
                name,
                startsAt,
                finishesAt,
                description
        );
    }
}
