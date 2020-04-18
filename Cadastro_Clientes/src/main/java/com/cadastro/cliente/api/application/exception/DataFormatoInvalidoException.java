package com.cadastro.cliente.api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DataFormatoInvalidoException extends RuntimeException{

    private static final long serialVersionUID = -1272508288712731125L;

    public DataFormatoInvalidoException() {
        super();
    }

    public DataFormatoInvalidoException(String message) {
        super(message);
    }

    public DataFormatoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
