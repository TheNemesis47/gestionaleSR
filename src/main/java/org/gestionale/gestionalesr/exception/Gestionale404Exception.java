package org.gestionale.gestionalesr.exception;

import org.gestionale.gestionalesr.config.exception.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Gestionale404Exception extends GenericException {
    public Gestionale404Exception(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
