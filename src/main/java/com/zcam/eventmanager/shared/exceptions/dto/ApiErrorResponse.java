package com.zcam.eventmanager.shared.exceptions.dto;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public record ApiErrorResponse(
        Instant timestamp,
        int status,
        Map<String, List<String>> errors
) {}
