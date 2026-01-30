package com.zcam.eventmanager.viacep.infra.exceptions;

public class CepNotFoundException extends RuntimeException {
    public CepNotFoundException(String message) {
        super(message);
    }
}
