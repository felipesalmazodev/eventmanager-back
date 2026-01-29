package com.zcam.eventmanager.event.dto;

import jakarta.validation.constraints.*;
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

        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Only numbers and letters are permitted")
        @Size(max = 255)
        String placeCode,

        @Size(max = 255)
        String description
) {
}
