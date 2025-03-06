package org.gestionale.gestionalesr.config.exception;

public abstract class GenericException extends RuntimeException {
    private final int statusCode;

    public GenericException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
