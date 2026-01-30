package com.zcam.eventmanager.viacep.infra.exceptions;

public class InvalidCepException extends RuntimeException {
    public InvalidCepException(String message) {
        super(message);
    }
}
