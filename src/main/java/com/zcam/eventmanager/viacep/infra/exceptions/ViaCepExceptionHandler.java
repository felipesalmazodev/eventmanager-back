package com.zcam.eventmanager.viacep.infra.exceptions;

import com.zcam.eventmanager.shared.exceptions.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ViaCepExceptionHandler {

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCepNotFound(CepNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                Map.of("cep", List.of(ex.getMessage()))
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidCepException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCep(InvalidCepException ex) {
        ApiErrorResponse response = new ApiErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                Map.of("cep", List.of(ex.getMessage()))
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ViaCepIntegrationException.class)
    public ResponseEntity<ApiErrorResponse> handleViaCepIntegration(ViaCepIntegrationException ex) {
        ApiErrorResponse response = new ApiErrorResponse(
                Instant.now(),
                HttpStatus.BAD_GATEWAY.value(),
                Map.of("cep", List.of(ex.getMessage()))
        );

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }
}
