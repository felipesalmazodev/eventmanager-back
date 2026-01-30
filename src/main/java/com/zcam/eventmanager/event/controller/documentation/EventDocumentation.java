package com.zcam.eventmanager.event.controller.documentation;

import com.zcam.eventmanager.event.dto.EventCreateRequest;
import com.zcam.eventmanager.event.dto.EventDetailsDto;
import com.zcam.eventmanager.event.dto.EventListDto;
import com.zcam.eventmanager.event.dto.EventUpdateRequest;
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

import java.util.List;

@Tag(name = "Events", description = "Event management endpoints")
public interface EventDocumentation {

    @Operation(
            summary = "List events",
            description = "List all the events from the database"
    )
    @ApiResponse(responseCode = "200")
    ResponseEntity<List<EventListDto>> getAllEvents();

    @Operation(
            summary = "Create an event",
            description = "Creates a new event with valid places by start and end date of the event"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
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
            @ApiResponse(responseCode = "400", description = "Bad request (business validation)",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "DateMismatchException",
                                            summary = "finishesAt is before startsAt",
                                            value = """
                                            {
                                                "timestamp": "2026-01-30T16:18:39.329328900Z",
                                                "status": 400,
                                                "errors": {
                                                    "dates": [
                                                        "The end date cannot be less than start date"
                                                    ]
                                                }
                                            }
                                            """
                                    ),
                                    @ExampleObject(
                                            name = "PlaceUnavailabilityException",
                                            summary = "The place is already booked in another Event",
                                            value = """
                                            {
                                                "timestamp": "2026-01-30T16:17:19.126896900Z",
                                                "status": 400,
                                                "errors": {
                                                    "place": [
                                                        "This place is not available for this date and hour"
                                                    ]
                                                }
                                            }
                                            """
                                    )
                            }
                    ))
    })
    ResponseEntity<?> create(EventCreateRequest request);

    @Operation(
            summary = "Get an event",
            description = "Get an event from the database and address details of the place if enrichPlace=true"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "ResourceNotFoundException",
                                            summary = "When the event does not exists",
                                            value = """
                                            {
                                                 "timestamp": "2026-01-30T16:39:44.251562100Z",
                                                 "status": 404,
                                                 "errors": {
                                                     "id": [
                                                         "Event with id '9' doesn't exists"
                                                     ]
                                                 }
                                             }
                                            """
                                    ),
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
    ResponseEntity<EventDetailsDto> getEventDetails(@Parameter(description = "Event ID") long id, @Parameter(description = "If true, include address details on the response") boolean enrichPlace);

    @Operation(
            summary = "Update an event",
            description = "Updates an event"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "ResourceNotFoundException",
                                            summary = "When the event does not exists",
                                            value = """
                                            {
                                                 "timestamp": "2026-01-30T16:39:44.251562100Z",
                                                 "status": 404,
                                                 "errors": {
                                                     "id": [
                                                         "Event with id '9' doesn't exists"
                                                     ]
                                                 }
                                             }
                                            """
                                    ),
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
            @ApiResponse(responseCode = "400", description = "Bad request (business validation)",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "DateMismatchException",
                                            summary = "finishesAt is before startsAt",
                                            value = """
                                            {
                                                "timestamp": "2026-01-30T16:18:39.329328900Z",
                                                "status": 400,
                                                "errors": {
                                                    "dates": [
                                                        "The end date cannot be less than start date"
                                                    ]
                                                }
                                            }
                                            """
                                    ),
                                    @ExampleObject(
                                            name = "PlaceUnavailabilityException",
                                            summary = "The place is already booked in another Event",
                                            value = """
                                            {
                                                "timestamp": "2026-01-30T16:17:19.126896900Z",
                                                "status": 400,
                                                "errors": {
                                                    "place": [
                                                        "This place is not available for this date and hour"
                                                    ]
                                                }
                                            }
                                            """
                                    )
                            }
                    ))
    })
    ResponseEntity<?> update(EventUpdateRequest request, @Parameter(description = "Event ID") long id);

    @Operation(
            summary = "Delete an event",
            description = "Deletes an event from the Database"
    )
    ResponseEntity<?> delete(@Parameter(description = "Event ID") long id);
}
