package com.cadastro.cliente.api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5613503359762451106L;

    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
}