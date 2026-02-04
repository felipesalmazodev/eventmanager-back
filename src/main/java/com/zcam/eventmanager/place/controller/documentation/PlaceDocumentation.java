package com.zcam.eventmanager.place.controller.documentation;

import com.zcam.eventmanager.place.dto.PlaceCreateRequest;
import com.zcam.eventmanager.place.dto.PlaceDetailsDto;
import com.zcam.eventmanager.place.dto.PlaceListDto;
import com.zcam.eventmanager.place.dto.PlaceUpdateRequest;
import com.zcam.eventmanager.shared.exceptions.dto.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Places", description = "Place management endpoints")
public interface PlaceDocumentation {

    @Operation(
            summary = "List places",
            description = "List all the places from the database"
    )
    @ApiResponse(responseCode = "200")
    ResponseEntity<List<PlaceListDto>> getAllPlaces();

    @Operation(
            summary = "Create a place",
            description = "Creates a new place"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "DuplicateResourceException",
                                            summary = "Another place with the same code already exists",
                                            value = """
                                            {
                                                 "timestamp": "2026-01-30T17:59:43.711236300Z",
                                                 "status": 409,
                                                 "errors": {
                                                     "code": [
                                                         "Place with code 'AUDCENTRAL10' already exists"
                                                     ]
                                                 }
                                             }
                                            """
                                    ),
                            }
                    ))
    })
    ResponseEntity<?> create(PlaceCreateRequest request);

    @Operation(
            summary = "Get a place",
            description = "Get a place from the database"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "ResourceNotFoundException",
                                            summary = "When the place does not exists",
                                            value = """
                                            {
                                                 "timestamp": "2026-01-30T16:39:44.251562100Z",
                                                 "status": 404,
                                                 "errors": {
                                                     "id": [
                                                         "Place with code 'AUDCENffTRAL7' doesn't exists."
                                                     ]
                                                 }
                                             }
                                            """
                                    )
                            }
                    ))
    })
    ResponseEntity<PlaceDetailsDto> getPlaceDetails(@Parameter(description = "Place ID") long id);

    @Operation(
            summary = "Update a place",
            description = "Updates a place"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "ResourceNotFoundException",
                                            summary = "When the place does not exists",
                                            value = """
                                            {
                                                 "timestamp": "2026-01-30T16:39:44.251562100Z",
                                                 "status": 404,
                                                 "errors": {
                                                     "id": [
                                                         "Place with code 'AUDCENffTRAL7' doesn't exists."
                                                     ]
                                                 }
                                             }
                                            """
                                    )
                            }
                    )),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "DuplicateResourceException",
                                            summary = "Another place with the same code already exists",
                                            value = """
                                            {
                                                 "timestamp": "2026-01-30T17:59:43.711236300Z",
                                                 "status": 409,
                                                 "errors": {
                                                     "code": [
                                                         "Place with code 'AUDCENTRAL10' already exists"
                                                     ]
                                                 }
                                             }
                                            """
                                    ),
                            }
                    ))
    })
    ResponseEntity<?> update(PlaceUpdateRequest request, @Parameter(description = "Place ID") long id);

    @Operation(
            summary = "Soft delete a place",
            description = "Soft deletes a place making active = false"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "ResourceNotFoundException",
                                            summary = "When the place does not exists",
                                            value = """
                                                    {
                                                         "timestamp": "2026-01-30T16:39:44.251562100Z",
                                                         "status": 404,
                                                         "errors": {
                                                             "id": [
                                                                 "Place with id 4 doesn't exists."
                                                             ]
                                                         }
                                                     }
                                                    """
                                    )
                            }
                    ))
    })
    ResponseEntity<?> delete(@Parameter(description = "Place ID") long id);

    @Operation(
            summary = "Returns a list of available places based on the start and finish date of a event"
    )
    ResponseEntity<List<PlaceListDto>> getAvailablePlacesBetween(@Parameter(description = "Event's start date") LocalDateTime startsAt,
                                                                 @Parameter(description = "Event's finish date") LocalDateTime finishesAt);
}
