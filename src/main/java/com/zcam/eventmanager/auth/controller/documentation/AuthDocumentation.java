package com.zcam.eventmanager.auth.controller.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;

import java.util.Map;

@Tag(name = "Authentication", description = "Authentication checker controller")
public interface AuthDocumentation {

    @Operation(
            summary = "Authentication checker",
            description = "Client can call this endpoint for checking if the user is still logged"
    )
    @ApiResponse(responseCode = "200")
    Map<String, Object> checkMe(Authentication authentication);
}
