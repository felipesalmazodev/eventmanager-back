package com.zcam.eventmanager.event.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Schema(description = "Request used to update an existing event")
public record EventUpdateRequest(

        @Schema(description = "Event name", example = "Java Conference 2026")
        @NotBlank(message = "This field is mandatory")
        @Size(max = 255)
        String name,

        @Schema(description = "Event start date and time", example = "2026-03-10T09:00:00")
        @NotNull(message = "This field is mandatory")
        @FutureOrPresent(message = "The date and hour cannot be in the past")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime startsAt,

        @Schema(description = "Event end date and time", example = "2026-03-10T18:00:00")
        @NotNull(message = "This field is mandatory")
        @FutureOrPresent(message = "The date and hour cannot be in the past")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime finishesAt,

        @Schema(description = "Unique code of the place where the event will occur", example = "AUDCENTRAL7")
        @NotBlank(message = "This field is mandatory")
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Only numbers and letters are permitted")
        @Size(max = 255)
        String placeCode,

        @Schema(description = "Optional event description", example = "Annual Java and Spring conference", nullable = true)
        @Size(max = 255)
        String description
) {
}
