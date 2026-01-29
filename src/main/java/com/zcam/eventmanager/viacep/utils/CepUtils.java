package com.zcam.eventmanager.viacep.utils;

import com.zcam.eventmanager.viacep.exceptions.InvalidCepException;

public final class CepUtils {

    private CepUtils() {}

    public static String normalize(String cepRaw) {
        if (cepRaw == null) return null;
        return cepRaw.replaceAll("\\D", "");
    }

    public static void validateOrThrow(String normalizedCep) {
        if (normalizedCep == null || normalizedCep.isBlank()) {
            throw new InvalidCepException("CEP is required");
        }
        if (!normalizedCep.matches("^\\d{8}$")) {
            throw new InvalidCepException("CEP must have exactly 8 digits (normalized). Got: " + normalizedCep);
        }
    }
}
