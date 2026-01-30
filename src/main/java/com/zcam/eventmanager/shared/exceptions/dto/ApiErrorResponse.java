package com.zcam.eventmanager.shared.exceptions.dto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Schema(description = "Standard error response returned by the API")
public record ApiErrorResponse(

        @Schema(description = "Date and time when the error occurred (UTC)", example = "2026-01-30T16:39:44.251562100Z")
        Instant timestamp,

        @Schema(description = "HTTP status code associated with the error", example = "400")
        int status,

        @Schema(
                description = "Map of field names to a list of validation or business error messages",
                example = "{ \"field\": [\"Error message describing the problem\"] }"
        )
        Map<String, List<String>> errors
) {}
