package com.cadastro.cliente.api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEnityException extends RuntimeException{

    private static final long serialVersionUID = 9169846267940483184L;

    public UnprocessableEnityException() {
        super();
    }

    public UnprocessableEnityException(String message) {
        super(message);
    }

    public UnprocessableEnityException(String message, Throwable cause) {
        super(message, cause);
    }
}
