package com.zcam.eventmanager.viacep.exceptions;

public class ViaCepIntegrationException extends RuntimeException {
    public ViaCepIntegrationException(String message) {
        super(message);
    }

    public ViaCepIntegrationException(String message, Throwable cause) { super(message, cause); }
}
